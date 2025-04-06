package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

     Customer save(Customer customer) throws ResourceAlreadyExistException;

     void delete(UUID id);
     CustomerDTO update(UUID id,CustomerDTO customerDTO) throws ResourceAlreadyExistException;
     CustomerDTO findCustomer(UUID id);
     List<CustomerDTO> findAll();
     List<CustomerDTO> findCustomerByIdAndCompany(UUID id, Company company);
     List<CustomerDTO> findCustomerByCompany(Company company);

     List<CustomerDTO> getCustomersByCompany(UUID companyId);
}
