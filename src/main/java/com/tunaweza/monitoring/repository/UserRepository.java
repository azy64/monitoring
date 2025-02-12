package com.tunaweza.monitoring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByUsername(String username);
    public User findByUsernameAndTypeUser(String username, TypeUser typeUser);
    public User findByIdAndTypeUser(UUID Id, TypeUser typeUser);
    public List<User> findAllByTypeUser(TypeUser typeUser);
}
