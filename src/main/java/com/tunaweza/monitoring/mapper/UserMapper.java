package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.UserDTO;
import com.tunaweza.monitoring.model.User;

public class UserMapper {
    public static UserDTO mapToDto(User user){
        if(user==null)return null;
        return UserDTO.builder()
            .birth(user.getBirth())
            .id(user.getId())
            .nom(user.getNom())
            .prenom(user.getPrenom())
            .typeUser(user.getTypeUser().getLabelling())
            .address(user.getAddress())
            .phoneNumber(user.getPhoneNumber())
            .pictureUser(user.getPictureUser())
            .employer(CompanyMapper.mapToDto(user.getEmployer()))
            .activated(user.getActivated())
            .isUsingMfa(user.getIsUsingMfa())
            .companies(user.getCompanies()!=null?
            user.getCompanies().stream().map(company->CompanyMapper.mapToDto(company)).toList()
            :null)
        .build();
    }

}
