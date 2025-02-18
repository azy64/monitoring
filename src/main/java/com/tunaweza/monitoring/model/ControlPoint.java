package com.tunaweza.monitoring.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class ControlPoint {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="around_id", nullable=false)
    private Around around;
    private String qrCode;
    private Long longitude;
    private Long latitude;
    private String label;
    private Date createAt;
    @OneToMany(mappedBy = "controlPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CheckPoint>checkPoints;
}
