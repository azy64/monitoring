package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.CustomerServiceInterface;
import com.tunaweza.monitoring.dto.CustomerInputDTO;
import com.tunaweza.monitoring.dto.CustomerOutputDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import com.tunaweza.monitoring.mapperDTO.CustomerMapper;
import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.Customer;
import com.tunaweza.monitoring.repository.CompanyRepository;
import com.tunaweza.monitoring.repository.CustomerRepository;
import com.tunaweza.monitoring.utils.ReferenceNumberGeneratorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CompanyRepository companyRepository;


    private final ReferenceNumberGeneratorInterface referenceNumberGenerator;


    @Override
    public CustomerOutputDTO save(CustomerInputDTO customerDTO) throws ResourceAlreadyExistException {

        if (customerRepository.findByEmail(customerDTO.getEmail()) != null) {
            throw new ResourceAlreadyExistException("Customer avec cet email existe déjà.");
        }

        Company company = companyRepository.findById(customerDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        // Mapper le DTO vers l'entité
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setCompany(company);
        customer.setReferenceNumber(referenceNumberGenerator.generateReferenceNumber(customer.getName()));

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerOutputDTO update(UUID id, CustomerInputDTO customerDTO) throws ResourceNotFoundException {
        // Récupérer l'ancien client
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Vérifier si la company existe
        Company company = companyRepository.findById(customerDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        // Mettre à jour les valeurs
        existingCustomer = customerMapper.updateEntityFromDTO(customerDTO, existingCustomer);
        existingCustomer.setCompany(company);

        // Sauvegarder
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.toDTO(updatedCustomer);
    }

    @Override
    public CustomerOutputDTO findCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerOutputDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerOutputDTO> getCustomersByCompany(UUID companyId) {
        List<Customer> customers = customerRepository.findByCompanyId(companyId);
        return Collections.singletonList(customerMapper.toDTO((Customer) customers));
    }
}
