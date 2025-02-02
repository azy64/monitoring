package com.tunaweza.monitoring.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CheckPoint {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="agent_id",nullable=false)
    private Agent agent;
    @ManyToOne
    @JoinColumn(name="controlPoint_id",nullable=false)
    private ControlPoint controlPoint;
    private String commentString;
    private Date checkedDate;
    private Boolean checkedPresence;

}
