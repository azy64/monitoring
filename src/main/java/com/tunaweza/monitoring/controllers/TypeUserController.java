package com.tunaweza.monitoring.controllers;

import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.services.TypeUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class TypeUserController {

    private final TypeUserService typeUserService;

    public TypeUserController(TypeUserService typeUserService) {
        this.typeUserService = typeUserService;
    }

    @GetMapping("getAllTypeUsers")
    public ResponseEntity<List<TypeUser>> getAllTypeUsers() {
        return ResponseEntity.ok(typeUserService.getAllTypeUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeUser> getTypeUserById(@PathVariable Long id) {
        Optional<TypeUser> typeUser = typeUserService.getTypeUserById(id);
        return typeUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createTypeUser")
    public ResponseEntity<TypeUser> createTypeUser(@RequestBody TypeUser typeUser) {
        return ResponseEntity.ok(typeUserService.createTypeUser(typeUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeUser(@PathVariable Long id) {
        typeUserService.deleteTypeUser(id);
        return ResponseEntity.noContent().build();
    }
}
