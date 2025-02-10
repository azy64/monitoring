package com.tunaweza.monitoring.controllers;


import com.tunaweza.monitoring.contract.CustomerServiceInterface;
import com.tunaweza.monitoring.dto.CustomerInputDTO;
import com.tunaweza.monitoring.dto.CustomerOutputDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerServiceInterface customerService;
    @PostMapping("/customer")
    public ResponseEntity<CustomerOutputDTO> createCustomer(@RequestBody CustomerInputDTO customerDTO) throws ResourceAlreadyExistException {
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerOutputDTO> getCustomer(@PathVariable UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok(customerService.findCustomer(id));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerOutputDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerOutputDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerInputDTO customerDTO) throws ResourceNotFoundException, ResourceAlreadyExistException {
        return ResponseEntity.ok(customerService.update(id, customerDTO));
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
