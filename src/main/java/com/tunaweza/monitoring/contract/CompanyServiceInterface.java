package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.model.Company;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.User;


public interface CompanyServiceInterface {

    public Company save(Company company);
    public void delete(UUID id);
    public Company update(Company company, UUID id);
    public Company findCompanyById(UUID id);
    public Company findCompanyByIdAndUser(UUID id,User owner);
    public List<Company> findAllByUser(User user);
    public List<Company> findAll();

}
