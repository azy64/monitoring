package com.tunaweza.monitoring.repository;

import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByEmail(String email);

    List<Customer> findByCompanyId(UUID companyId);
    List<Customer> findByIdAndCompany(UUID Id,Company company);
    List<Customer> findByCompany(Company company);
}
