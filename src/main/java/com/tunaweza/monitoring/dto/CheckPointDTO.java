package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.UUID;

import com.tunaweza.monitoring.model.ControlPoint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckPointDTO {

    private UUID id;
    private AgentDTO agent;
    private ControlPointDTO controlPoint;
    private String commentString;
    private Date checkedDate;
    private Boolean checkedPresence;
}
