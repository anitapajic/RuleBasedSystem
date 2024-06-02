package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.dto.user.UserDTO;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.user.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO authenticationRequest) throws AuthenticationException {

        return ResponseEntity.ok(userService.login(authenticationRequest));
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.register(userDTO));
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }

    }

    @PreAuthorize("hasAnyRole('PATIENT')")
    @GetMapping(value = "/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        try {
            User user = userService.getByEmail(email);
            return ResponseEntity.ok(new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getRoles().stream().toList().get(0).getName()));
        }
        catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }
}
