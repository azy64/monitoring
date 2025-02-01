package com.tunaweza.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Agent;

public interface AgentRepository  extends JpaRepository<Agent, Long>{
    public Agent findByEmail(String email);
}
