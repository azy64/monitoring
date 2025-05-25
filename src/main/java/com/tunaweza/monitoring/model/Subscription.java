package com.tunaweza.monitoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double amount;
    private Date subscribeDate;
    private Date subscribeEndDate;
    private String subscribeState;
    private Date createAt;

    @OneToOne(mappedBy = "subscription", orphanRemoval = true)
    private Company company;

    @OneToMany(mappedBy = "subscription", orphanRemoval = true)
    private List<Payment> payments;

}
