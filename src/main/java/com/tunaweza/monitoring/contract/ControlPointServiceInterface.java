package com.tunaweza.monitoring.contract;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.dto.ControlPointDTO;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.ControlPoint;

public interface ControlPointServiceInterface {

    public ControlPoint save(ControlPoint controlPoint);
    public void delete(UUID id);
    public ControlPoint update(ControlPoint controlPoint, UUID id);
    public ControlPoint findById(UUID id);
    public List<ControlPointDTO> findControlPointByAround(Around around);
    public List<ControlPoint> findAll();
    public ControlPoint findControlPointByCreateAt(Date createAt);

}
