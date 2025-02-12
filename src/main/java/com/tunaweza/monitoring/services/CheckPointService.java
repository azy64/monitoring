package com.tunaweza.monitoring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tunaweza.monitoring.contract.CheckPointServiceInterface;
import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;
import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.CheckPointRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CheckPointService implements  CheckPointServiceInterface{

    private final CheckPointRepository checkPointRepository;
    @Override
    public CheckPoint save(CheckPoint checkPoint) {
        return checkPointRepository.save(checkPoint);
    }

    @Override
    public void delete(UUID id) {
        CheckPoint checkPoint = checkPointRepository.findById(id).orElse(null);
        if(checkPoint!=null) checkPointRepository.delete(checkPoint);
        else
         throw new UnsupportedOperationException("Id:"+id+" was not found!");
    }

    @Override
    public CheckPoint update(CheckPoint checkPoint, UUID id) {
        CheckPoint previousCheckPoint = checkPointRepository.findById(id).orElse(null);
        if(previousCheckPoint!=null){
            previousCheckPoint.setAgent(checkPoint.getAgent());
            previousCheckPoint.setCheckedDate(checkPoint.getCheckedDate());
            previousCheckPoint.setCheckedPresence(checkPoint.getCheckedPresence());
            previousCheckPoint.setCommentString(checkPoint.getCommentString());
            previousCheckPoint.setControlPoint(checkPoint.getControlPoint());
            return checkPointRepository.save(previousCheckPoint);
        }
        else
            throw new UnsupportedOperationException("The checkPoint with the id:"+id+" does not exist");
    }

    @Override
    public CheckPoint findAgent(UUID id) {
        return checkPointRepository.findById(id).orElse(null);
    }

    @Override
    public List<CheckPoint> findAll() {
        return checkPointRepository.findAll();
    }

    @Override
    public List<CheckPoint> findCheckPointByAgentAndControlPoint(User agent, ControlPoint controlPoint){
        return checkPointRepository.findCheckPointByAgentAndControlPoint(agent, controlPoint);
    }

    @Override
    public List<CheckPoint> findCheckPointByAgent(User agent){
        return checkPointRepository.findCheckPointByAgent(agent);
    }
    
    @Override
    public List<CheckPoint> findCheckPointByControlPoint(ControlPoint controlPoint){
        return checkPointRepository.findCheckPointByControlPoint(controlPoint);
    }

}
