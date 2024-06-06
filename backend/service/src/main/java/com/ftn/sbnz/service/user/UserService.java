package com.ftn.sbnz.service.user;

import com.ftn.sbnz.model.dto.user.UserDTO;
import com.ftn.sbnz.model.dto.user.UserTokenState;
import com.ftn.sbnz.model.models.user.Patient;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.model.models.user.Role;
import com.ftn.sbnz.repository.PatientRepository;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.repository.RoleRepository;
import com.ftn.sbnz.util.TokenUtils;
import com.ftn.sbnz.util.exceptions.AlreadyExistisException;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserTokenState login(UserDTO authenticationRequest) throws AuthenticationException {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();

            String jwt = tokenUtils.generateToken(user);
            int expiresIn = tokenUtils.getExpiredIn();

            return new UserTokenState(jwt, expiresIn, user.getRoles().stream().toList().get(0).getName(), user.getId());
        }
        catch(Exception e){
            throw new AuthenticationException("aaaaa");
        }

    }

    public UserDTO register(UserDTO userDTO) {
        User validation = userRepository.findByEmail(userDTO.getEmail());
        if (validation != null) throw new AlreadyExistisException("User with this email already exists.");

        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(userDTO.getRole()));

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setRoles(roles);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());

        User savedUser = userRepository.save(user);

        Patient patient = new Patient();
        patient.setId(savedUser.getId());
        patient.setWeight(userDTO.getWeight());
        patient.setBirthDate(userDTO.getBirthDate());
        patient.setName(savedUser.getName());
        patient.setSurname(savedUser.getSurname());
        patient.setPassword(savedUser.getPassword());
        patient.setEmail(savedUser.getEmail());
        patient.setRoles(savedUser.getRoles());

        patientRepository.save(patient);

        userDTO.setId(user.getId());
        userDTO.setPassword("");
        return userDTO;
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This user doesn't exist"));
    }

}
