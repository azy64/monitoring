package com.tunaweza.monitoring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/connection")
@RequiredArgsConstructor
public class ConnectionController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userRepository.findByUserName(user.getUsername())!=null)
            return ResponseEntity.badRequest().body("user name already exist");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?>loginUser(@RequestBody User user){
        try {
            authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return ResponseEntity.ok("login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid username and password");
        }
    }

}
