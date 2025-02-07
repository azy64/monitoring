package com.tunaweza.monitoring.controllers;


import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.CompanyServiceInterface;
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
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = jwtAuthenticationToken.getToken();
        User user= userRepository.findByUsername(jwt.getSubject());
        company.setOwner(user);
        System.out.println(" company:"+company.getOwner().getId());
        return ResponseEntity.ok(companyService.save(company));
    }

}
