package com.tunaweza.monitoring.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AroundInputDTO {
    private String label;
    private String address;
    private String controlPointNumber;
    private UUID customerId;
}
