package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.dto.CustomerDTO;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Customer;
import java.util.Date;

public class AroundMapper {

    public static AroundDTO mapToDto(Around around) {
        if (around == null) {
            return null;
        }

        AroundDTO.AroundDTOBuilder builder = AroundDTO.builder()
                .id(around.getId())
                .label(around.getLabel())
                .address(around.getAddress())
                .createAt(around.getCreateAt())
                .controlPointNumber(around.getControlPointNumber());

        if (around.getCustomer() != null) {
            builder.customer(CustomerDTO.builder()
                    .id(around.getCustomer().getId())
                    .name(around.getCustomer().getName())
                    .address(around.getCustomer().getAddress())
                    .build());
        }

        return builder.build();
    }

    public static Around mapToEntity(AroundDTO aroundDTO) {
        if (aroundDTO == null) {
            return null;
        }

        Around.AroundBuilder builder = Around.builder()
                .id(aroundDTO.getId())
                .label(aroundDTO.getLabel())
                .address(aroundDTO.getAddress())
                .controlPointNumber(aroundDTO.getControlPointNumber())
                .createAt(new Date());

        if (aroundDTO.getCustomer() != null) {
            builder.customer(Customer.builder()
                    .id(aroundDTO.getCustomer().getId())
                    .name(aroundDTO.getCustomer().getName())
                    .address(aroundDTO.getCustomer().getAddress())
                    .build());
        }

        return builder.build();
    }
}