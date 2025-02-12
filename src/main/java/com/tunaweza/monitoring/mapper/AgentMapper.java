package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AgentDTO;
import com.tunaweza.monitoring.model.User;

public class AgentMapper {

    public static AgentDTO mapToDto(User agent){
        return AgentDTO.builder()
            .birth(agent.getBirth())
            .id(agent.getId())
            .nom(agent.getNom())
            .prenom(agent.getPrenom())
            .typeUser(agent.getTypeUser().getLabelling())
            .address(agent.getAddress())
            .phoneNumber(agent.getPhoneNumber())
            .pictureUser(agent.getPictureUser())
            .employer(CompanyMapper.mapToDto(agent.getEmployer()))
            .activated(agent.getActivated())
            .isUsingMfa(agent.getIsUsingMfa())
        .build();
    }
}
