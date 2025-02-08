package com.tunaweza.monitoring.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @OneToMany(orphanRemoval=true, mappedBy="owner")
    @JsonIgnore
    private List<Company> Company;
    private Boolean activated;
    private Boolean isUsingMfa;

}
