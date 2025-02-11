package com.tunaweza.monitoring.controllers;

import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.dto.AroundInputDTO;
import com.tunaweza.monitoring.dto.AroundOutputDTO;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AroundController {
    private final AroundServiceInterface aroundService;


    @PostMapping("/around")
    public ResponseEntity<AroundOutputDTO> createAround(@RequestBody AroundInputDTO aroundDTO) {
        return ResponseEntity.ok(aroundService.save(aroundDTO));
    }

    @GetMapping("/around/{id}")
    public ResponseEntity<AroundOutputDTO> getAround(@PathVariable UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok(aroundService.findAroundById(id));
    }

    @GetMapping("/arounds")
    public ResponseEntity<List<AroundOutputDTO>> getAllArounds() {
        return ResponseEntity.ok(aroundService.findAll());
    }

    @PutMapping("/around/{id}")
    public ResponseEntity<AroundOutputDTO> updateAround(@PathVariable UUID id, @RequestBody AroundInputDTO aroundDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(aroundService.update(id, aroundDTO));
    }

    @DeleteMapping("/around/{id}")
    public ResponseEntity<Void> deleteAround(@PathVariable UUID id) {
        aroundService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/around/customer/{customerId}")
    public ResponseEntity<List<AroundOutputDTO>> getAroundsByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(aroundService.findAroundByCustomer(customerId));
    }

    @GetMapping("/around/company/{companyId}")
    public ResponseEntity<List<AroundOutputDTO>> getAroundsByCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(aroundService.getAroundsByCompany(companyId));
    }
}
