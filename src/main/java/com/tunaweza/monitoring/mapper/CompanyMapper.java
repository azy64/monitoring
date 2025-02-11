package com.tunaweza.monitoring.mapper;

import java.util.stream.Collectors;

import com.tunaweza.monitoring.dto.CompanyDTO;
import com.tunaweza.monitoring.model.Company;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CompanyMapper {

    public static CompanyDTO mapToDto(Company company){
        return CompanyDTO.builder()
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
            .customers(company.getCustomers()!=null?company.getCustomers().stream().map(customer->CustomerMapper.mapToDto(customer)).collect(Collectors.toList()):null)
            .build();
    }

    public static Company mapToEntity(CompanyDTO companyDto){
        return Company.builder()
        .activated(companyDto.getActivated())
        .createAt(companyDto.getCreateAt())
        .email(companyDto.getEmail())
        .id(companyDto.getId())
        //.agents(
        //    companyDto.getAgents()!=null ?
        //    companyDto.getAgents().stream().map(agentDto->AgentMapper.mapToEntity(agentDto)).collect(Collectors.toList())
        //    :null)

        .customers(companyDto.getCustomers()!=null?
        companyDto.getCustomers().stream().map(customerDto->CustomerMapper.mapToEntity(customerDto)).collect(Collectors.toList())
        :null)
        
        .name(companyDto.getName())
        .phone(companyDto.getPhone())
        .address(companyDto.getAddress())
        .siret(companyDto.getSiret())
            //.referenceNumber(companyDto.getReferenceNumber())
            //.subscription(company.getSubscription().getId())
        .build();
    }
}
