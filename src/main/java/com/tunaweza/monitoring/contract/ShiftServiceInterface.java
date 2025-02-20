package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;
import com.tunaweza.monitoring.model.User;

public interface ShiftServiceInterface {
     public Shift save(Shift shift);
    public void delete(UUID id);
    public Shift update(Shift shift, UUID id);
    public Shift findById(UUID id);
    public List<Shift> findShiftByAgentAndAround(User agent, Around around);
    public List<Shift> findShiftByAgent(User agent);
    public List<Shift> findShiftByAround(Around around);
    public List<Shift> findAll();

    Shift findActiveShiftByAgentAndAround(User agent, Around around);

}
