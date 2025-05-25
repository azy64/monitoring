package com.tunaweza.monitoring.repository;

import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    public List<Company> findAllByOwner(User owner);
    public Company findOneByIdAndOwner(UUID id, User owner);
}
