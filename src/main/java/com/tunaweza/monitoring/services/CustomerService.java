package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.CustomerServiceInterface;
import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import com.tunaweza.monitoring.mapper.CustomerMapper;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.Customer;
import com.tunaweza.monitoring.repository.CompanyRepository;
import com.tunaweza.monitoring.repository.CustomerRepository;
import com.tunaweza.monitoring.utils.ReferenceNumberGeneratorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tunaweza.monitoring.mapper.CustomerMapper.mapToDto;
import static com.tunaweza.monitoring.mapper.CustomerMapper.mapToEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final ReferenceNumberGeneratorInterface referenceNumberGenerator;


    @Override
    public CustomerDTO save(CustomerDTO customerDTO) throws ResourceAlreadyExistException {

        if (customerRepository.findByEmail(customerDTO.getEmail()) != null) {
            throw new ResourceAlreadyExistException("Customer avec cet email existe déjà.");
        }

        Company company = companyRepository.findById(customerDTO.getCompany().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Customer customer = mapToEntity(customerDTO);
        customer.setCompany(company);
        customer.setReferenceNumber(referenceNumberGenerator.generateReferenceNumber(customer.getName()));

        Customer savedCustomer = customerRepository.save(customer);

        return mapToDto(savedCustomer);
    }

    @Override
    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO update(UUID id, CustomerDTO customerDTO) throws ResourceNotFoundException {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Company company = companyRepository.findById(customerDTO.getCompany().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setSiret(customerDTO.getSiret());
        existingCustomer.setCompany(company);

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return mapToDto(updatedCustomer);
    }

    @Override
    public CustomerDTO findCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return mapToDto(customer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CustomerDTO> getCustomersByCompany(UUID companyId) {
        List<Customer> customers = customerRepository.findByCompanyId(companyId);
        return customers.stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<CustomerDTO> findCustomerByIdAndCompany(UUID id, Company company){
        List<Customer> customers = customerRepository.findByIdAndCompany(id,company);
        return customers.stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<CustomerDTO> findCustomerByCompany(Company company){
        List<Customer> customers = customerRepository.findByCompany(company);
        return customers.stream()
                .map(CustomerMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
