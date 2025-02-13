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

import com.tunaweza.monitoring.contract.ShiftServiceInterface;
import com.tunaweza.monitoring.mapper.ShiftMapper;
import com.tunaweza.monitoring.model.Shift;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/")
public class ShiftController {
    private final ShiftServiceInterface  shiftService;

    @PostMapping("/shift")
    public ResponseEntity<?> createShift(@RequestBody Shift shift){
        return ResponseEntity.ok(ShiftMapper.mapToDto(shiftService.save(shift)));
    }
    
    @GetMapping("/shift/{id}")
    public ResponseEntity<?> getShift(@PathVariable UUID id){
        return ResponseEntity.ok(
            ShiftMapper.mapToDto(shiftService.findById(id))
        );
    }

    @GetMapping("/shift")
    public ResponseEntity<?> getShifts(){
        return ResponseEntity.ok(
            shiftService.findAll().stream()
            .map(shift->ShiftMapper.mapToDto(shift)).toList()
        );
    }

    @DeleteMapping("/shift/{id}")
    public ResponseEntity<?>deleteCheckPoint(@PathVariable UUID id){
        shiftService.delete(id);
        return ResponseEntity.ok("The Shift has been deleted");
    }

    @PutMapping("/shift/{id}")
    public ResponseEntity<?> updateCheckPoint(@PathVariable UUID id, @RequestBody Shift shift){

        return ResponseEntity.ok(
           ShiftMapper.mapToDto(shiftService.update(shift, id))
        );
    }

}
