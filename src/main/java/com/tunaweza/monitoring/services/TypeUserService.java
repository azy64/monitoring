package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.repository.TypeUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeUserService {

    private final TypeUserRepository typeUserRepository;

    public TypeUserService(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    public List<TypeUser> getAllTypeUsers() {
        return typeUserRepository.findAll();
    }

    public Optional<TypeUser> getTypeUserById(Long id) {
        return typeUserRepository.findById(id);
    }

    public TypeUser createTypeUser(TypeUser typeUser) {
        return typeUserRepository.save(typeUser);
    }

    public void deleteTypeUser(Long id) {
        typeUserRepository.deleteById(id);
    }
}
