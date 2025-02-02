package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.ControlPoint;

public interface ControlPointServiceInterface {

    public ControlPoint save(ControlPoint controlPoint) throws ResourceAlreadyExistException;
    public void delete(UUID id);
    public ControlPoint update(ControlPoint controlPoint, UUID id);
    public ControlPoint findAgent(UUID id);
    public List<ControlPoint> findControlPointByAround(Around around);
    public List<ControlPoint> findAll();

}
