package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.dto.AroundInputDTO;
import com.tunaweza.monitoring.dto.AroundOutputDTO;
import com.tunaweza.monitoring.model.*;

import java.util.List;
import java.util.UUID;

public interface AroundServiceInterface {
    AroundOutputDTO save(AroundInputDTO aroundDTO);
     void delete(UUID id);
    AroundOutputDTO update(UUID id, AroundInputDTO aroundDTO);

    AroundOutputDTO findAroundById(UUID id);

     List<AroundOutputDTO> findAll();
    //public List<Around> findAroundByShift(Shift shift);
     List<AroundOutputDTO> findAroundByCustomer(UUID customerId);

     List<AroundOutputDTO> getAroundsByCompany(UUID companyId);



}
