package com.tunaweza.monitoring.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.tunaweza.monitoring.dto.ControlPointDTO;
import com.tunaweza.monitoring.mapper.ControlPointMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tunaweza.monitoring.contract.ControlPointServiceInterface;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.ControlPoint;
import com.tunaweza.monitoring.repository.ControlPointRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ControlPointService implements ControlPointServiceInterface {

    private final ControlPointRepository controlPointRepository;
    @Override
    public ControlPoint save(ControlPoint controlPoint) {
       return  controlPointRepository.save(controlPoint);
    }

    @Override
    public void delete(UUID id) {
        ControlPoint controlPoint = controlPointRepository.findById(id).orElse(null);
        if(controlPoint!=null) controlPointRepository.delete(controlPoint);
    }

    @Override
    public ControlPoint update(ControlPoint controlPoint, UUID id) {
        ControlPoint previousControlPoint = controlPointRepository.findById(id).orElse(null);
        if(previousControlPoint!=null){
            previousControlPoint.setAround(controlPoint.getAround());
            previousControlPoint.setLabel(controlPoint.getLabel());
            previousControlPoint.setLatitude(controlPoint.getLatitude());
            previousControlPoint.setLongitude(controlPoint.getLongitude());
            previousControlPoint.setQrCode(controlPoint.getQrCode());
            controlPointRepository.save(previousControlPoint);
        }
        return previousControlPoint;
    }

    @Override
    public ControlPoint findById(UUID id) {
        return controlPointRepository.findById(id).orElse(null);
    }

    @Override
    public List<ControlPoint> findAll() {
        return controlPointRepository.findAll();
    }

    @Override
    public List<ControlPointDTO> findControlPointByAround(Around around) {
        return controlPointRepository.findControlPointByAround(around).stream()
                .map(ControlPointMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ControlPoint findControlPointByCreateAt(Date createAt){
        return controlPointRepository.findControlPointByCreateAt(createAt);
    }
}

