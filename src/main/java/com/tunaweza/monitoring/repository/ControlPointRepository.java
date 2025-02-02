package com.tunaweza.monitoring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.ControlPoint;

public interface ControlPointRepository extends JpaRepository<ControlPoint, UUID>{
    public List<ControlPoint> findControlPointByAround(Around around);
}
