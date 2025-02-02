package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;

public interface ShiftServiceInterface {
     public Shift save(Shift shift);
    public void delete(UUID id);
    public Shift update(Shift shift, UUID id);
    public Shift findAgent(UUID id);
    public List<Shift> findShiftByAgentAndAround(Agent agent, Around around);
    public List<Shift> findShiftByAgent(Agent agent);
    public List<Shift> findShiftByAround(Around around);
    public List<Shift> findAll();
}
