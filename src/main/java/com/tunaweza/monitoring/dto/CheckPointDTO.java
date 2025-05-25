package com.tunaweza.monitoring.dto;

import java.util.Date;
import java.util.UUID;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckPointDTO {

    private UUID id;
    private AgentDTO agent;
    private UUID controlPointId;
    private String label;
    private String commentString;
    private Date checkedDate;
    private Boolean checkedPresence;
}
