package com.tunaweza.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Agent;

import java.util.List;
import java.util.UUID;

public interface AgentRepository  extends JpaRepository<Agent, Long>{
    public Agent findByEmail(String email);

    List<Agent> findByCompanyId(UUID companyId);
}
