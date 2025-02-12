package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShiftDTO {

    private UUID id;
    private AgentDTO agent;
    private AroundDTO around;
    private Date shifDate;
    private Date shiftStarTime;
    private Date shiftEndTime;
}
