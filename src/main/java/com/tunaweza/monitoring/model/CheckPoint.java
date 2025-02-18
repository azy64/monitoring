package com.tunaweza.monitoring.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CheckPoint {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User agent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controlPoint_id", nullable = false)
    private ControlPoint controlPoint;
    private String commentString;
    private Date checkedDate;
    private Boolean checkedPresence;

}
