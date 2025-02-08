package com.tunaweza.monitoring.mapperDTO;

import com.tunaweza.monitoring.dto.CustomerInputDTO;
import com.tunaweza.monitoring.dto.CustomerOutputDTO;
import com.tunaweza.monitoring.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Convertir Customer -> CustomerOutputDTO
    public CustomerOutputDTO toDTO(Customer customer) {
        CustomerOutputDTO dto = new CustomerOutputDTO();
        BeanUtils.copyProperties(customer, dto);
        if (customer.getCompany() != null) {
            dto.setCompanyId(customer.getCompany().getId()); // Ajouter l'ID de la société
        }
        return dto;
    }

    // Convertir CustomerInputDTO -> Customer (Sans ID, il sera généré automatiquement)
    public Customer toEntity(CustomerInputDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    // Convertir CustomerInputDTO -> Customer (Avec ID pour la mise à jour)
    public Customer updateEntityFromDTO(CustomerInputDTO dto, Customer existingCustomer) {
        BeanUtils.copyProperties(dto, existingCustomer, "id"); // Ne pas modifier l'ID
        return existingCustomer;
    }
}
