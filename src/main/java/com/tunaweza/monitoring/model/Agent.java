package com.tunaweza.monitoring.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Agent{
    private String nom;
    private String prenom;
    private String email;
    private String passwordAgent;
    private Date birthAgent;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy="agent", orphanRemoval = true)
    private List<Shift>shifts;
    @OneToMany(mappedBy="agent", orphanRemoval = true)
    private List<CheckPoint>checkPoints;
    private String referenceNumber;
    private Boolean activated;
    private Date createAt;

}