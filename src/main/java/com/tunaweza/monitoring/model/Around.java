package com.tunaweza.monitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Around {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String label;
    private String address;
    private String controlPointNumber;
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "around", orphanRemoval = true)
    private List<ControlPoint> controlPoints;

    @OneToMany(mappedBy = "around",orphanRemoval=true)
    private List<Shift>shifts;

}
