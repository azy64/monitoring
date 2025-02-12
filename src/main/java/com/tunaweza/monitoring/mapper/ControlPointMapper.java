package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.ControlPointDTO;
import com.tunaweza.monitoring.model.ControlPoint;

public class ControlPointMapper {

    public static ControlPointDTO mapToDto(ControlPoint controlPoint){
        return ControlPointDTO.builder()
        .around(AroundMapper.mapToDto(controlPoint.getAround()))
        .createAt(controlPoint.getCreateAt())
        .id(controlPoint.getId())
        .label(controlPoint.getLabel())
        .latitude(controlPoint.getLatitude())
        .longitude(controlPoint.getLongitude())
        .qrCode(controlPoint.getQrCode())
        .checkPoints(controlPoint.getCheckPoints()!=null ?
        controlPoint.getCheckPoints().stream().map(checkPoint->CheckPointMapper.mapToDto(checkPoint)).toList()
        :null
        )
        .build();
    }
}
