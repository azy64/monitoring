package com.tunaweza.monitoring.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class CustomerOutputDTO {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String referenceNumber;
    private String siret;
    private UUID companyId;
}
