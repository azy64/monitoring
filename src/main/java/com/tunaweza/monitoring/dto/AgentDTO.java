package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AgentDTO {

    private UUID id;
    private String nom;
    private String prenom;
    private Date birth;
    private String phoneNumber;
    private String address;
    private String email;
    private String pictureUser;
    private String typeUser;
    private CompanyDTO employer;
    private Boolean activated;
    private Boolean isUsingMfa;
}
