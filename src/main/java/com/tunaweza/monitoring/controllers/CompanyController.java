package com.tunaweza.monitoring.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.CompanyServiceInterface;
import com.tunaweza.monitoring.mapper.CompanyMapper;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyServiceInterface companyService;
    private final UserRepository userRepository;
    @PostMapping("/company")
    public ResponseEntity<?> createCompany(
        @RequestBody Company company
    ){
        try {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = jwtAuthenticationToken.getToken();
            User user= userRepository.findByUsername(jwt.getSubject());
            company.setOwner(user);
            return ResponseEntity.ok(CompanyMapper.mapToDto(companyService.save(company)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("droit incorrecte:");
        }
        
    }

}
