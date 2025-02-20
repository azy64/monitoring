package com.tunaweza.monitoring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.Shift;
import com.tunaweza.monitoring.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, UUID>{
    public List<Shift> findShiftByAgentAndAround(User agent, Around around);
    public List<Shift> findShiftByAgent(User agent);
    public List<Shift> findShiftByAround(Around around);

    List<Shift> findActiveShiftsByAgentAndAround(
            @Param("agent") User agent,
            @Param("around") Around around
    );
}
