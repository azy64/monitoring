package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CompanyDTO {
    private UUID id;
    private String name;
    private String email;

    private Boolean activated;
    private String referenceNumber;
    private String siret;

    private String phone;

    private String address;

    private Date createAt;


    private List<CustomerDTO> customers;

     private List<AgentDto> agents;

    private UUID subscription;

    private String username;

}
