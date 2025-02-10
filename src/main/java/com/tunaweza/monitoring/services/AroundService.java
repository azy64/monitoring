package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.dto.AroundInputDTO;
import com.tunaweza.monitoring.dto.AroundOutputDTO;
import com.tunaweza.monitoring.exception.ResourceNotFoundException;
import com.tunaweza.monitoring.mapperDTO.AroundMapper;
import com.tunaweza.monitoring.model.*;
import com.tunaweza.monitoring.repository.AroundRepository;
import com.tunaweza.monitoring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AroundService implements AroundServiceInterface {

    private final AroundRepository aroundRepository;
    private final CustomerRepository customerRepository;
    private final AroundMapper aroundMapper;


    @Override
    public AroundOutputDTO save(AroundInputDTO aroundDTO) {
        Customer customer = customerRepository.findById(aroundDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Around around = aroundMapper.toEntity(aroundDTO);
        around.setCustomer(customer);

        Around savedAround = aroundRepository.save(around);

        return aroundMapper.toDTO(savedAround);

    }

    @Override
    public void delete(UUID id) {
        if (!aroundRepository.existsById(id)) {
            throw new ResourceNotFoundException("Around not found");
        }
        aroundRepository.deleteById(id);
    }

    @Override
    public AroundOutputDTO update(UUID id, AroundInputDTO aroundDTO) throws ResourceNotFoundException {
        Around existingAround = aroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Around not found"));

        Customer customer = customerRepository.findById(aroundDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Mettre à jour les valeurs
        existingAround.setLabel(aroundDTO.getLabel());
        existingAround.setAddress(aroundDTO.getAddress());
        existingAround.setControlPointNumber(aroundDTO.getControlPointNumber());
        existingAround.setCustomer(customer);

        // Sauvegarder
        Around updatedAround = aroundRepository.save(existingAround);
        return aroundMapper.toDTO(updatedAround);
    }

    @Override
    public AroundOutputDTO findAroundById(UUID id) {
        Around around = aroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Around not found"));
        return aroundMapper.toDTO(around);

    }

    @Override
    public List<AroundOutputDTO> findAll() {
        return aroundMapper.toDTOList(aroundRepository.findAll());
    }

   /* @Override
    public List<Around> findAroundByShift(Shift shift) {
        return aroundRepository.findAroundByShift(shift);

    }
    */

    @Override
    public List<AroundOutputDTO> findAroundByCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<Around> arounds = aroundRepository.findAroundByCustomer(customer);

        return aroundMapper.toDTOList(arounds);
    }

    public List<AroundOutputDTO> getAroundsByCompany(UUID companyId) {
        List<Around> arounds = aroundRepository.findByCustomer_Company_Id(companyId);
        return aroundMapper.toDTOList(arounds);
    }

}
