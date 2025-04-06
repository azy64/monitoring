package com.tunaweza.monitoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TypeUser {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;
    protected String labelling;

}
