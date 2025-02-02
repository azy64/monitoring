package com.tunaweza.monitoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String name;

    private String email;

    private String phone;
    private String address;

    private String referenceNumber;

    private String siret;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private Date createAt;
}
