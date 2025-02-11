package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Agent;

public interface AgentServiceInterface {

     Agent save(Agent agent) throws ResourceAlreadyExistException;
     void delete(UUID id);
     Agent update(Agent agent, UUID id);
     Agent findAgent(UUID id);
     Agent findAgentByUsername(String username);
     List<Agent> findAll();

     List<Agent> getAgentsByCompany(UUID companyId);

}
