package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.CompanyDto;
import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.model.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class CustomerMapper {

    public static CustomerDTO mapToDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .referenceNumber(customer.getReferenceNumber())
                .siret(customer.getSiret())
                .company(CompanyDto.builder()
                        .id(customer.getCompany().getId())
                        .name(customer.getCompany().getName())
                        .address(customer.getCompany().getAddress())
                        .build())
                .build();
    }


    public static Customer mapToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .referenceNumber(customerDTO.getReferenceNumber())
                .siret(customerDTO.getSiret())
                .createAt(new Date())
                .build();
    }

}
