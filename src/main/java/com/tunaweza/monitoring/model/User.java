package com.tunaweza.monitoring.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String pictureUser;
    private String role;
    private Date birth;
    private String nom;
    private String prenom;
    @ManyToOne
    private TypeUser typeUser;
    /**
     * companies I created---------
     */
    @OneToMany(orphanRemoval=true, mappedBy="owner")
    private List<Company> Companies;

    /**
     * my employer----
     */
    @ManyToOne
    @JoinColumn(nullable = true)
    private Company employer;
    //------------------------------------------------
    @OneToMany(mappedBy="agent", orphanRemoval = true)
    private List<Shift>shifts;
    @OneToMany(mappedBy="agent", orphanRemoval = true)
    private List<CheckPoint>checkPoints;
    private String referenceNumber;
    private Boolean activated;
    private Boolean isUsingMfa;

}
