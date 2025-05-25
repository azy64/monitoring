package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControlPointDTO {

    private UUID id;
    private AroundDTO around;
    private String qrCode;
    private Long longitude;
    private Long latitude;
    private String label;
    private Date createAt;
    private List<CheckPointDTO>checkPoints;
}
