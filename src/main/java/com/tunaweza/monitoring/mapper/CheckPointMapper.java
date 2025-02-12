package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.CheckPointDTO;
import com.tunaweza.monitoring.model.CheckPoint;

public class CheckPointMapper {

    public static CheckPointDTO mapToDto(CheckPoint checkpoint){
        return CheckPointDTO.builder()
        .id(checkpoint.getId())
        .agent(AgentMapper.mapToDto(checkpoint.getAgent()))
        .checkedDate(checkpoint.getCheckedDate())
        .checkedPresence(checkpoint.getCheckedPresence())
        .commentString(checkpoint.getCommentString())
        .controlPoint(ControlPointMapper.mapToDto(checkpoint.getControlPoint()))
        .build();
    }
}
