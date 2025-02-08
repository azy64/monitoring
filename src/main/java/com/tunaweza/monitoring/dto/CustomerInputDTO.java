package com.tunaweza.monitoring.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class CustomerInputDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String referenceNumber;
    private String siret;
    private UUID companyId;
}
