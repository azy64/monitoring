package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

     private UUID id;
    private String nom;
    private String prenom;
    private Date birth;
    private String phoneNumber;
    private String address;
    private String pictureUser;
    private String typeUser;
    private CompanyDTO employer;
    private Boolean activated;
    private Boolean isUsingMfa;
    private List<CompanyDTO> companies;
}
