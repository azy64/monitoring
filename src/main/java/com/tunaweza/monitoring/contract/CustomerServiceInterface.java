package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Company;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

     CustomerDTO save(CustomerDTO customerDTO) throws ResourceAlreadyExistException;

     void delete(UUID id);
     CustomerDTO update(UUID id,CustomerDTO customerDTO) throws ResourceAlreadyExistException;
     CustomerDTO findCustomer(UUID id);
     List<CustomerDTO> findAll();
     List<CustomerDTO> findCustomerByIdAndCompany(UUID id, Company company);
     List<CustomerDTO> findCustomerByCompany(Company company);

     List<CustomerDTO> getCustomersByCompany(UUID companyId);
}
