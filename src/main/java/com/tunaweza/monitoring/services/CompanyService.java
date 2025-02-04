package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.CompanyServiceInterface;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.repository.CompanyRepository;
import com.tunaweza.monitoring.utils.ReferenceNumberGeneratorInterface;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.tunaweza.monitoring.model.User;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepository companyRepository;
    private final ReferenceNumberGeneratorInterface referenceNumberGenerator;
    private final Authentication authentication;


    @Override
    public Company save(Company company) {
        if (company.getReferenceNumber() == null || company.getReferenceNumber().isEmpty()) {
            company.setReferenceNumber(referenceNumberGenerator.generateReferenceNumber(company.getName()));
        }
        return companyRepository.save(company);
    }

    @Override
    public void delete(UUID id) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company!=null) companyRepository.delete(company);
        else
            throw new UnsupportedOperationException("Id:"+id+" was not found!");

    }

    @Override
    public Company update(Company company, UUID id) {
        Company previousCompany = companyRepository.findById(id).orElse(null);
        if(previousCompany!=null){
            previousCompany.setName(company.getName());
            previousCompany.setEmail(company.getEmail());
            previousCompany.setPhone(company.getPhone());
            previousCompany.setAddress(company.getAddress());
            previousCompany.setSiret(company.getSiret());
            previousCompany.setActivated(company.getActivated());
            previousCompany.setSubscription(company.getSubscription());
            return companyRepository.save(previousCompany);
        }
        else
            throw new UnsupportedOperationException("The company with the id:"+id+" does not exist");

    }

    @Override
    public Company findCompanyById(UUID id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> findAllByUserId(UUID userId){
        return companyRepository.findAllByUserId(userId);
    }
}
