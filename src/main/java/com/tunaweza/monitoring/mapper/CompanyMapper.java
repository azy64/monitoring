package com.tunaweza.monitoring.mapper;

import java.util.stream.Collectors;

import com.tunaweza.monitoring.dto.CompanyDto;
import com.tunaweza.monitoring.model.Company;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CompanyMapper {

    public static CompanyDto mapToDto(Company company){
        return CompanyDto.builder()
            .id(company.getId())
            .activated(company.getActivated())
            .username(company.getOwner().getUsername())
            .createAt(company.getCreateAt())
            .email(company.getEmail())
            .name(company.getName())
            .phone(company.getPhone())
            .address(company.getAddress())
            .siret(company.getSiret())
            .referenceNumber(company.getReferenceNumber())
            .subscription(company.getSubscription()!=null?company.getSubscription().getId():null)
            .agents(company.getAgents()!=null?company.getAgents().stream().map(agent->AgentMapper.mapToDto(agent)).collect(Collectors.toList()):null)
            .build();
    }

    public static Company mapToEntity(CompanyDto companyDto){
        return Company.builder()
        .activated(companyDto.getActivated())
        .createAt(companyDto.getCreateAt())
        .email(companyDto.getEmail())
        .id(companyDto.getId())
        .agents(companyDto.getAgents().stream().map(agentDto->AgentMapper.mapToEntity(agentDto)).collect(Collectors.toList()))
        
        .name(companyDto.getName())
        .phone(companyDto.getPhone())
        .address(companyDto.getAddress())
        .siret(companyDto.getSiret())
            //.referenceNumber(companyDto.getReferenceNumber())
            //.subscription(company.getSubscription().getId())
        .build();
    }
}
