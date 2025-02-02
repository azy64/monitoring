package com.tunaweza.monitoring.model;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String passwordAgent;
    private Date birthAgent;
    @ManyToOne
    private Company company;
    private String referenceNumber;
    private Boolean activated;
    private Date createAt;


}