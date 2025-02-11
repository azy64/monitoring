package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;
import com.tunaweza.monitoring.model.User;

public interface CheckPointServiceInterface {

    public CheckPoint save(CheckPoint checkPoint);
    public void delete(UUID id);
    public CheckPoint update(CheckPoint checkPoint, UUID id);
    public CheckPoint findAgent(UUID id);
    public List<CheckPoint> findAll();
    public List<CheckPoint> findCheckPointByAgentAndControlPoint(User agent, ControlPoint controlPoint);
    public List<CheckPoint> findCheckPointByAgent(User agent);
    public List<CheckPoint> findCheckPointByControlPoint(ControlPoint controlPoint);
}
