package com.tunaweza.monitoring.controllers;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("We can save this company");
        }
        
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getOneCompany(@PathVariable UUID id){
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }

    @GetMapping("/company")
    public ResponseEntity<?> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAll().stream()
        .map(company->CompanyMapper.mapToDto(company)).collect(Collectors.toList()));
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable UUID id){
        companyService.delete(id);
        return ResponseEntity.ok("the deletion was successful");
    }
    
    @PutMapping("/company/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable UUID id, @RequestBody Company company){
        return ResponseEntity.ok(CompanyMapper.mapToDto(companyService.update(company, id)));
    }

}
