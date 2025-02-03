package com.tunaweza.monitoring.repository;

import com.tunaweza.monitoring.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
