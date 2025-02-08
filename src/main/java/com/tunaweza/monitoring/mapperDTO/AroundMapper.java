package com.tunaweza.monitoring.mapperDTO;

import com.tunaweza.monitoring.dto.AroundInputDTO;
import com.tunaweza.monitoring.dto.AroundOutputDTO;
import com.tunaweza.monitoring.model.Around;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AroundMapper {

    // Convertir Around -> AroundOutputDTO
    public AroundOutputDTO toDTO(Around around) {
        AroundOutputDTO dto = new AroundOutputDTO();
        BeanUtils.copyProperties(around, dto);
        if (around.getCustomer() != null) {
            dto.setCustomerId(around.getCustomer().getId()); // On récupère seulement l'ID du client
        }
        return dto;
    }

    // Convertir AroundInputDTO -> Around
    public Around toEntity(AroundInputDTO dto) {
        Around around = new Around();
        BeanUtils.copyProperties(dto, around);
        return around;
    }

    // Convertir une liste d'entités Around en liste de DTOs
    public List<AroundOutputDTO> toDTOList(List<Around> arounds) {
        return arounds.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
