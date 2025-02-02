package com.tunaweza.monitoring.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;

public interface CheckPointRepository  extends JpaRepository<CheckPoint, UUID>{
    public List<CheckPoint> findCheckPointByAgentAndControlPoint(Agent agent, ControlPoint controlPoint);
    public List<CheckPoint> findCheckPointByAgent(Agent agent);
    public List<CheckPoint> findCheckPointByControlPoint(ControlPoint controlPoint);

}
