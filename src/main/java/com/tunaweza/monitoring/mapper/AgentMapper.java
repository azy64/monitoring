package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AgentDto;
import com.tunaweza.monitoring.model.Agent;

public class AgentMapper {

    public static AgentDto mapToDto(Agent agent){
        return AgentDto.builder()
            .birthAgent(agent.getBirthAgent())
            .id(agent.getId())
            .nom(agent.getNom())
            .prenom(agent.getPrenom())
        .build();
    }

    public static Agent mapToEntity(AgentDto agentDto){
        return Agent.builder()
            .birthAgent(agentDto.getBirthAgent())
            .id(agentDto.getId())
            .nom(agentDto.getNom())
            .prenom(agentDto.getPrenom())
        .build();
    }
}
