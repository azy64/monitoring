package com.tunaweza.monitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String referenceNumber;
    private String siret;
    private CompanyBasicDTO company;
}