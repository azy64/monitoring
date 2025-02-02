package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;

public interface CheckPointServiceInterface {

    public CheckPoint save(CheckPoint checkPoint);
    public void delete(UUID id);
    public CheckPoint update(CheckPoint checkPoint, UUID id);
    public CheckPoint findAgent(UUID id);
    public List<CheckPoint> findAll();
    public List<CheckPoint> findCheckPointByAgentAndControlPoint(Agent agent, ControlPoint controlPoint);
    public List<CheckPoint> findCheckPointByAgent(Agent agent);
    public List<CheckPoint> findCheckPointByControlPoint(ControlPoint controlPoint);
}
