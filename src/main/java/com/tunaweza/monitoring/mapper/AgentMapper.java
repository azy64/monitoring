package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.AgentDTO;
import com.tunaweza.monitoring.model.User;

public class AgentMapper {

    public static AgentDTO mapToDto(User agent){
        if(agent==null)return null;
        return AgentDTO.builder()
            .birth(agent.getBirth())
            .id(agent.getId())
            .nom(agent.getNom())
            .prenom(agent.getPrenom())
            .email(agent.getUsername())
            .typeUser(agent.getTypeUser() != null ? agent.getTypeUser().getLabelling() : null)
            .address(agent.getAddress())
            .phoneNumber(agent.getPhoneNumber())
            .pictureUser(agent.getPictureUser())
            //.employer(CompanyMapper.mapToDto(agent.getEmployer()!=null?agent.getEmployer():null))
            .activated(agent.getActivated())
            .isUsingMfa(agent.getIsUsingMfa())
        .build();
    }
}
