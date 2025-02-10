package com.tunaweza.monitoring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AgentDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthAgent;
}
