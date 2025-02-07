package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

     Customer save(Customer customer) throws ResourceAlreadyExistException;
     void delete(UUID id);
     Customer update(Customer customer, UUID id) throws ResourceAlreadyExistException;
     Customer findCustomer(UUID id);
     List<Customer> findAll();

     List<Customer> getCustomersByCompany(UUID companyId);
}
