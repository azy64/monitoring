package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.model.Around;
import java.util.Date;

public class AroundMapper {

    public static AroundDTO mapToDto(Around around) {
        return AroundDTO.builder()
                .id(around.getId())
                .label(around.getLabel())
                .address(around.getAddress())
                .controlPointNumber(around.getControlPointNumber())
                .customer(CustomerDTO.builder()
                        .id(around.getCustomer().getId())
                        .name(around.getCustomer().getName())
                        .address(around.getCustomer().getAddress())
                        .build())
                .build();
    }


    public static Around mapToEntity(AroundDTO aroundDTO) {
        return Around.builder()
                .id(aroundDTO.getId())
                .label(aroundDTO.getLabel())
                .address(aroundDTO.getAddress())
                .controlPointNumber(aroundDTO.getControlPointNumber())
                .createAt(new Date())
                .build();
    }
}
