package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.CheckPointDTO;
import com.tunaweza.monitoring.model.CheckPoint;

public class CheckPointMapper {

    public static CheckPointDTO mapToDto(CheckPoint checkpoint){
        if(checkpoint == null) return null;
        return CheckPointDTO.builder()
                .id(checkpoint.getId())
                .agent(AgentMapper.mapToDto(checkpoint.getAgent()))
                .checkedDate(checkpoint.getCheckedDate())
                .checkedPresence(checkpoint.getCheckedPresence())
                .commentString(checkpoint.getCommentString())
                .controlPointId(checkpoint.getControlPoint().getId())
                .label(checkpoint.getControlPoint().getLabel())
                .build();
    }
}
