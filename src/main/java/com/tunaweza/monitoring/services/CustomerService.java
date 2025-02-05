package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.CustomerServiceInterface;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Customer;
import com.tunaweza.monitoring.repository.CustomerRepository;
import com.tunaweza.monitoring.utils.ReferenceNumberGeneratorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final ReferenceNumberGeneratorInterface referenceNumberGenerator;


    @Override
    public Customer save(Customer customer) throws ResourceAlreadyExistException {

        Customer customerExisted = customerRepository.findByEmail(customer.getEmail());
        if (customerExisted != null) {
            throw new ResourceAlreadyExistException("Customer existe déjà");
        }

        if (customer.getReferenceNumber() == null || customer.getReferenceNumber().isEmpty()) {
            customer.setReferenceNumber(referenceNumberGenerator.generateReferenceNumber(customer.getName()));
        }

        return customerRepository.save(customer);
    }

    @Override
    public void delete(UUID id) {
        if(customerRepository.existsById(id))
            customerRepository.deleteById(id);

    }

    @Override
    public Customer update(Customer customer, UUID id) throws ResourceAlreadyExistException {
        Customer oldCustomer = customerRepository.findById(id).orElse(null);
        if(oldCustomer == null) throw new ResourceAlreadyExistException("Customer not found");
        oldCustomer.setName(customer.getName());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setSiret(customer.getSiret());
        return customerRepository.save(oldCustomer);
    }

    @Override
    public Customer findCustomer(UUID id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer>getCustomersByCompany(UUID companyId) {
        return customerRepository.findByCompanyId(companyId);
    }
}
