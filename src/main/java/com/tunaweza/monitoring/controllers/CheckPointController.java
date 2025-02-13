package com.tunaweza.monitoring.controllers;

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
import com.tunaweza.monitoring.mapper.CheckPointMapper;
import com.tunaweza.monitoring.model.CheckPoint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CheckPointController {

    private final CheckPointServiceInterface checkPointService;
    @PostMapping("/check-point")
    public ResponseEntity<?> createCheckPoint(@RequestBody CheckPoint checkPoint){
        return ResponseEntity.ok(CheckPointMapper.mapToDto(checkPointService.save(checkPoint)));
    }
    
    @GetMapping("/check-point/{id}")
    public ResponseEntity<?> getCheckPoint(@PathVariable UUID id){
        return ResponseEntity.ok(
            CheckPointMapper.mapToDto(checkPointService.findById(id))
        );
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
