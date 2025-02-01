package com.tunaweza.monitoring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tunaweza.monitoring.contract.AgentServiceInterface;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.model.Agent;
import com.tunaweza.monitoring.repository.AgentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AgentService implements  AgentServiceInterface{

    private final AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) throws ResourceAlreadyExistException {
        Agent agentExisted = agentRepository.findByEmail(agent.getEmail());
        if(agentExisted!=null) throw new ResourceAlreadyExistException("Agent existe dejà");
        return agentRepository.save(agent);
    }

    @Override
    public void delete(Long id) {
        if(agentRepository.existsById(id))
            agentRepository.deleteById(id);
        
    }

    @Override
    public Agent update(Agent agent, Long id) {
        Agent oldAgent = agentRepository.findById(id).get();
        oldAgent.setNom(agent.getNom());
        oldAgent.setPrenom(agent.getPrenom());
        oldAgent.setBirthAgent(agent.getBirthAgent());
        oldAgent.setActivated(agent.getActivated());
        oldAgent.setEmail(agent.getEmail());
        oldAgent.setPasswordAgent(agent.getPasswordAgent());
        return agentRepository.save(oldAgent);
    }

    @Override
    public Agent findAgent(Long id) {
        return agentRepository.findById(id).get();
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

}
