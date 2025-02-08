package com.tunaweza.monitoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private Boolean activated;

    @Column(unique = true)
    private String referenceNumber;

    @Column(unique = true)
    private String siret;

    private String phone;

    private String address;

    private Date createAt;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Customer> customers;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Agent> agents;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;
}
