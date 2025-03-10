package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.model.Customer;

import java.util.List;
import java.util.UUID;

public interface AroundServiceInterface {
    AroundDTO save(AroundDTO aroundDTO);
     void delete(UUID id);
    AroundDTO update(UUID id, AroundDTO aroundDTO);

    AroundDTO findAroundById(UUID id);

     List<AroundDTO> findAll();
    //public List<Around> findAroundByShift(Shift shift);
     List<AroundDTO> findAroundByCustomer(UUID customerId);
     List<AroundDTO> findByCustomer(Customer customer);

     List<AroundDTO> getAroundsByCompany(UUID companyId);

}
