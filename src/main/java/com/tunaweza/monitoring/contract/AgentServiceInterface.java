package com.tunaweza.monitoring.contract;

import java.util.List;

import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Agent;

public interface AgentServiceInterface {

    public Agent save(Agent agent) throws ResourceAlreadyExistException;
    public void delete(Long id);
    public Agent update(Agent agent, Long id);
    public Agent findAgent(Long id);
    public List<Agent> findAll();

}
