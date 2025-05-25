package com.tunaweza.monitoring.controllers;

import java.util.Optional;
import java.util.UUID;

import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.mapper.AroundMapper;
import com.tunaweza.monitoring.model.Around;
import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tunaweza.monitoring.contract.ShiftServiceInterface;
import com.tunaweza.monitoring.mapper.ShiftMapper;
import com.tunaweza.monitoring.model.Shift;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/")
public class ShiftController {
    private final ShiftServiceInterface  shiftService;
    private final UserRepository userRepository;
    private final AroundServiceInterface aroundService;

    @PostMapping("/shift")
    public ResponseEntity<?> createShift(@RequestBody Shift shift){
        System.out.println("shift:::"+shift.toString());
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

    @GetMapping("/active-shift")
    public ResponseEntity<?> getActiveShift(
            @RequestParam UUID agentId,
            @RequestParam UUID aroundId
    ) {
        Optional<User> agent = userRepository.findById(agentId);
        AroundDTO aroundDTO = aroundService.findAroundById(aroundId);

        if (agent.isEmpty() || aroundDTO == null) {
            return ResponseEntity.badRequest().body("Agent or Around not found");
        }

        // Convertir AroundDTO en Around si nécessaire
        Around around = AroundMapper.mapToEntity(aroundDTO);

        Shift activeShift = shiftService.findActiveShiftByAgentAndAround(agent.get(), around);

        return activeShift != null
                ? ResponseEntity.ok(ShiftMapper.mapToDto(activeShift))
                : ResponseEntity.ok(null);
    }



}
