package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import com.tunaweza.monitoring.mapper.AroundMapper;
import com.tunaweza.monitoring.model.*;
import com.tunaweza.monitoring.repository.AroundRepository;
import com.tunaweza.monitoring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.tunaweza.monitoring.mapper.AroundMapper.mapToDto;
import static com.tunaweza.monitoring.mapper.AroundMapper.mapToEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class AroundService implements AroundServiceInterface {

    private final AroundRepository aroundRepository;
    private final CustomerRepository customerRepository;


    @Override
    public AroundDTO save(AroundDTO aroundDTO) {
        Customer customer = customerRepository.findById(aroundDTO.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Around around = mapToEntity(aroundDTO);
        around.setCustomer(customer);

        Around savedAround = aroundRepository.save(around);

        return mapToDto(savedAround);

    }

    @Override
    public void delete(UUID id) {
        if (!aroundRepository.existsById(id)) {
            throw new ResourceNotFoundException("Around not found");
        }
        aroundRepository.deleteById(id);
    }

    @Override
    public AroundDTO update(UUID id, AroundDTO aroundDTO) throws ResourceNotFoundException {
        Around existingAround = aroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Around not found"));

        Customer customer = customerRepository.findById(aroundDTO.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Mettre à jour les valeurs
        existingAround.setLabel(aroundDTO.getLabel());
        existingAround.setAddress(aroundDTO.getAddress());
        existingAround.setControlPointNumber(aroundDTO.getControlPointNumber());
        existingAround.setCustomer(customer);

        // Sauvegarder
        Around updatedAround = aroundRepository.save(existingAround);
        return mapToDto(updatedAround);
    }

    @Override
    public AroundDTO findAroundById(UUID id) {
        Around around = aroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Around not found"));
        return mapToDto(around);

    }

    @Override
    public List<AroundDTO> findAll() {
        return aroundRepository.findAll().stream().map(AroundMapper::mapToDto).toList();
    }

   /* @Override
    public List<Around> findAroundByShift(Shift shift) {
        return aroundRepository.findAroundByShift(shift);

    }
    */

    @Override
    public List<AroundDTO> findAroundByCustomer(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        List<Around> arounds = aroundRepository.findAroundByCustomer(customer);
        return arounds.stream().map(AroundMapper::mapToDto).toList();
    }

    @Override
    public List<AroundDTO> getAroundsByCompany(UUID companyId) {
        return aroundRepository.findByCustomer_Company_Id(companyId).stream().map(AroundMapper::mapToDto).toList();
    }

}
