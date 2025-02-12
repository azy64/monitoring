package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AgentDTO;
import com.tunaweza.monitoring.dto.ShiftDTO;
import com.tunaweza.monitoring.model.Shift;

public class ShiftMapper {
    public static ShiftDTO mapToDto(Shift shift){
        return ShiftDTO.builder()
        .agent(AgentMapper.mapToDto(shift.getAgent()))
        .around(AroundMapper.mapToDto(shift.getAround()))
        .shifDate(shift.getShifDate())
        .shiftStarTime(shift.getShiftStarTime())
        .shiftEndTime(shift.getShiftEndTime())
        .build();
    }

    public static Shift mapToEntity(ShiftDTO shiftDTO){
        return Shift.builder()
        //.agent()
        .around(AroundMapper.mapToEntity(shiftDTO.getAround()))
        .shifDate(shiftDTO.getShifDate())
        .shiftStarTime(shiftDTO.getShiftStarTime())
        .shiftEndTime(shiftDTO.getShiftEndTime())
        .build();
    }
}
