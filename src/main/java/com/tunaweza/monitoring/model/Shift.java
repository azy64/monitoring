package com.tunaweza.monitoring.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Shift {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User agent;
    @ManyToOne
    @JoinColumn(name = "around_id", nullable = false)
    private Around around;
    private Date shifDate;
    private Date shiftStarTime;
    private Date shiftEndTime;

}
