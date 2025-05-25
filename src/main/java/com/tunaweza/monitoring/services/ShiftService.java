package com.tunaweza.monitoring.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tunaweza.monitoring.contract.ShiftServiceInterface;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;
import com.tunaweza.monitoring.model.User;
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
        return shiftRepository.findById(id)
                .map(existingShift -> {
                    Optional.ofNullable(shift.getShiftEndTime()).ifPresent(existingShift::setShiftEndTime);
                    Optional.ofNullable(shift.getAgent()).ifPresent(existingShift::setAgent);
                    Optional.ofNullable(shift.getShiftDate()).ifPresent(existingShift::setShiftDate);
                    Optional.ofNullable(shift.getShiftStartTime()).ifPresent(existingShift::setShiftStartTime);
                    Optional.ofNullable(shift.getAround()).ifPresent(existingShift::setAround);

                    return shiftRepository.save(existingShift);
                })
                .orElseThrow(() -> new UnsupportedOperationException("The shift with that id:" + id + " does not exist."));
    }

    @Override
    public Shift findById(UUID id) {
        return shiftRepository.findById(id).orElse(null);
    }

    @Override
    public List<Shift> findShiftByAgentAndAround(User agent, Around around) {
        return shiftRepository.findShiftByAgentAndAround(agent, around);
    }

    @Override
    public List<Shift> findShiftByAgent(User agent) {
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

    @Override
    public Shift findActiveShiftByAgentAndAround(User agent, Around around) {
        List<Shift> activeShifts = shiftRepository.findActiveShiftsByAgentAndAround(agent, around);

        if (activeShifts.isEmpty()) {
            return null;
        }

        return activeShifts.get(0);
    }

}
