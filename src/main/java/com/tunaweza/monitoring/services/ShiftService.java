package com.tunaweza.monitoring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tunaweza.monitoring.contract.ShiftServiceInterface;
import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;
import com.tunaweza.monitoring.repository.ShiftRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ShiftService  implements  ShiftServiceInterface{

    private final ShiftRepository shiftRepository;
    @Override
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public void delete(UUID id) {
        Shift shift = shiftRepository.findById(id).orElse(null);
        if(shift!=null) shiftRepository.delete(shift);
        else
            throw new UnsupportedOperationException("The shift with that id:"+id+" does not exist.");
    }

    @Override
    public Shift update(Shift shift, UUID id) {
        Shift previousShift = shiftRepository.findById(id).orElse(null);
        if(previousShift!=null) {
            previousShift.setShiftEndTime(shift.getShiftEndTime());
            previousShift.setAgent(shift.getAgent());
            previousShift.setShifDate(shift.getShifDate());
            previousShift.setShiftStarTime(shift.getShiftStarTime());
            previousShift.setAround(shift.getAround());
            return shiftRepository.save(previousShift);
        }
        else
            throw new UnsupportedOperationException("The shift with that id:"+id+" does not exist.");
    }

    @Override
    public Shift findAgent(UUID id) {
        return shiftRepository.findById(id).orElse(null);
    }

    @Override
    public List<Shift> findShiftByAgentAndAround(Agent agent, Around around) {
        return shiftRepository.findShiftByAgentAndAround(agent, around);
    }

    @Override
    public List<Shift> findShiftByAgent(Agent agent) {
        return shiftRepository.findShiftByAgent(agent);
    }

    @Override
    public List<Shift> findShiftByAround(Around around) {
        return shiftRepository.findShiftByAround(around);
    }

    @Override
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

}
