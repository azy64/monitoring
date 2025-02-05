package com.tunaweza.monitoring.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.CompanyServiceInterface;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyServiceInterface companyService;
    @PostMapping("/company")
    public ResponseEntity<?> createCompany(
        @RequestBody Company company,@RequestBody User user
    ){
        System.out.println("user:"+user.getUsername()+" company:"+company.getOwner().getId());
        return ResponseEntity.ok(companyService.save(company));
    }

}
