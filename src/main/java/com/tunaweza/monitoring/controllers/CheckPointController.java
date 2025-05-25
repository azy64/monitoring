package com.tunaweza.monitoring.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.CheckPointServiceInterface;
import com.tunaweza.monitoring.contract.ControlPointServiceInterface;
import com.tunaweza.monitoring.dto.CheckPointDTO;
import com.tunaweza.monitoring.mapper.CheckPointMapper;
import com.tunaweza.monitoring.model.CheckPoint;
import com.tunaweza.monitoring.model.ControlPoint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CheckPointController {

    private final CheckPointServiceInterface checkPointService;
    private final ControlPointServiceInterface controlPointService;
    @PostMapping("/check-point")
    public ResponseEntity<?> createCheckPoint(@RequestBody CheckPoint checkPoint){
        System.out.println("checkpoint value:::"+ checkPoint.toString());
        return ResponseEntity.ok(CheckPointMapper.mapToDto(checkPointService.save(checkPoint)));
    }
    /**
     * a special route to save CheckPoint by Post method with missing param
     */
    @PostMapping("/check-point/special")
    public ResponseEntity<?> createCheckPointWithoutSomeParams(@RequestBody CheckPoint checkPoint){
        ControlPoint controlPoint=controlPointService.findControlPointByCreateAt(checkPoint.getControlPoint().getCreateAt());
        checkPoint.setControlPoint(controlPoint);
        return ResponseEntity.ok(CheckPointMapper.mapToDto(checkPointService.save(checkPoint)));
    }

    
    @GetMapping("/check-point/{id}")
    public ResponseEntity<?> getCheckPoint(@PathVariable UUID id){
        return ResponseEntity.ok(
            CheckPointMapper.mapToDto(checkPointService.findById(id))
        );
    }

    @GetMapping("/check-point/user/{id}")
    public ResponseEntity<List<CheckPointDTO>> getCheckPointByAgent(@PathVariable UUID id) {
        List<CheckPointDTO> checkPointsDto = checkPointService.findByAgent_Id(id)
                .stream()
                .map(CheckPointMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(checkPointsDto);
    }

    @GetMapping("/check-point")
    public ResponseEntity<?> getCheckPoint(){
        return ResponseEntity.ok(
            checkPointService.findAll().stream()
            .map(checkpoint->CheckPointMapper.mapToDto(checkpoint)).toList()
        );
    }

    @DeleteMapping("/check-point/{id}")
    public ResponseEntity<?>deleteCheckPoint(@PathVariable UUID id){
        checkPointService.delete(id);
        return ResponseEntity.ok("Checkpoint has been deleted");
    }

    @PutMapping("/check-point/{id}")
    public ResponseEntity<?> updateCheckPoint(@PathVariable UUID id, @RequestBody CheckPoint checkPoint){

        return ResponseEntity.ok(
           CheckPointMapper.mapToDto(checkPointService.update(checkPoint, id))
        );
    }
}
