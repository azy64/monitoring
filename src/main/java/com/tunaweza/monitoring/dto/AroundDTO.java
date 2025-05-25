package com.tunaweza.monitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AroundDTO {
    private UUID id;
    private String label;
    private String address;
    private String controlPointNumber;
    private CustomerDTO customer;
    private Date createAt;
}
