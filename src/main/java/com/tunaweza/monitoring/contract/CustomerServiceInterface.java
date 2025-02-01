package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

    public Customer save(Customer customer) throws ResourceAlreadyExistException;
    public void delete(UUID id);
    public Customer update(Customer customer, UUID id) throws ResourceAlreadyExistException;
    public Customer findCustomer(UUID id);
    public List<Customer> findAll();
}
