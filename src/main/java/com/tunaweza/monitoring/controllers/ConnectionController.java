package com.tunaweza.monitoring.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.dto.RefreshTokenRequest;
import com.tunaweza.monitoring.dto.UserDTO;
import com.tunaweza.monitoring.mapper.UserMapper;
import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.TypeUserRepository;
import com.tunaweza.monitoring.repository.UserRepository;
import com.tunaweza.monitoring.services.JWTService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ConnectionController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TypeUserRepository typeUserRepository;

    private final JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        //user.setRole("AGENT");
        if (user.getTypeUser() != null && user.getTypeUser().getId() != null) {
            Optional<TypeUser> typeUser = typeUserRepository.findById(user.getTypeUser().getId());
            if (typeUser.isPresent()) {
                user.setTypeUser(typeUser.get());
            } else {
                return ResponseEntity.badRequest().body("Invalid TypeUser ID");
            }
        } else {
            return ResponseEntity.badRequest().body("TypeUser is required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            Map<String, String> tokens = jwtService.generateTokens(authentication);

            User authenticatedUser = userRepository.findByUsername(user.getUsername());
            UserDTO userDTO = UserMapper.mapToDto(authenticatedUser);

            Map<String, Object> response = new HashMap<>();
            response.put("access_token", tokens.get("access_token"));
            response.put("refresh_token", tokens.get("refresh_token"));
            response.put("user", userDTO);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            String newAccessToken = jwtService.refreshAccessToken(request.getRefreshToken());
            return ResponseEntity.ok(Map.of("access_token", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
