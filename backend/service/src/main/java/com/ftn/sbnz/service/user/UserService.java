package com.ftn.sbnz.service.user;

import com.ftn.sbnz.model.dto.user.UserDTO;
import com.ftn.sbnz.model.dto.user.UserTokenState;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.model.models.user.Role;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.repository.RoleRepository;
import com.ftn.sbnz.util.TokenUtils;
import com.ftn.sbnz.util.exceptions.AlreadyExistisException;
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

        // da vidimo da li cemo raditi sa maperima
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(userDTO.getRole()));

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        userRepository.save(user);
        userDTO.setId(user.getId());
        userDTO.setPassword("");
        return userDTO;
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
