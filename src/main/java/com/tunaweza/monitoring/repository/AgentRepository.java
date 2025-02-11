package com.tunaweza.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Agent;

import java.util.List;
import java.util.UUID;

public interface AgentRepository  extends JpaRepository<Agent, UUID>{
    public Agent findByEmail(String email);
    public Agent findByUsername(String username);

    List<Agent> findByCompanyId(UUID companyId);
}
