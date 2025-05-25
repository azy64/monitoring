package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.ShiftDTO;
import com.tunaweza.monitoring.model.Shift;

public class ShiftMapper {
    public static ShiftDTO mapToDto(Shift shift){
        if(shift==null) return null;
        return ShiftDTO.builder()
        .agent(AgentMapper.mapToDto(shift.getAgent()))
        .around(AroundMapper.mapToDto(shift.getAround()))
        .id(shift.getId())
        .shifDate(shift.getShiftDate())
        .shiftStarTime(shift.getShiftStartTime())
        .shiftEndTime(shift.getShiftEndTime())
        .build();
    }

    public static Shift mapToEntity(ShiftDTO shiftDTO){
        if(shiftDTO==null) return null;
        return Shift.builder()
        //.agent()
        .around(AroundMapper.mapToEntity(shiftDTO.getAround()))
        .shiftDate(shiftDTO.getShifDate())
        .shiftStartTime(shiftDTO.getShiftStarTime())
        .shiftEndTime(shiftDTO.getShiftEndTime())
        .build();
    }
}
