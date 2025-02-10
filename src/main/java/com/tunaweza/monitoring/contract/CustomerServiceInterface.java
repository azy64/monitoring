package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.dto.CustomerInputDTO;
import com.tunaweza.monitoring.dto.CustomerOutputDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

     CustomerOutputDTO save(CustomerInputDTO customerDTO) throws ResourceAlreadyExistException;

     void delete(UUID id);
     CustomerOutputDTO update(UUID id,CustomerInputDTO customerDTO) throws ResourceAlreadyExistException;
     CustomerOutputDTO findCustomer(UUID id);
     List<CustomerOutputDTO> findAll();

     List<CustomerOutputDTO> getCustomersByCompany(UUID companyId);
}
