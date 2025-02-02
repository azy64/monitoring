package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.CheckPoint;

public interface CheckPointServiceInterface {

    public CheckPoint save(CheckPoint checkPoint);
    public void delete(UUID id);
    public CheckPoint update(CheckPoint checkPoint, UUID id);
    public CheckPoint findAgent(UUID id);
    public List<CheckPoint> findAll();
}
