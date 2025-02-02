package com.tunaweza.monitoring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, UUID>{
    public List<Shift> findShiftByAgentAndAround(Agent agent, Around around);
    public List<Shift> findShiftByAgent(Agent agent);
    public List<Shift> findShiftByAround(Around around);
}
