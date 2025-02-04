package com.tunaweza.monitoring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByUserName(String username);
}
