package com.tunaweza.monitoring.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;
import com.tunaweza.monitoring.model.User;

public interface CheckPointRepository  extends JpaRepository<CheckPoint, UUID>{
    public List<CheckPoint> findCheckPointByAgentAndControlPoint(User agent, ControlPoint controlPoint);
    public List<CheckPoint> findCheckPointByAgent(User agent);
    public List<CheckPoint> findCheckPointByControlPoint(ControlPoint controlPoint);

}
