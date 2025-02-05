package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.model.*;

import java.util.List;
import java.util.UUID;

public interface AroundServiceInterface {
     Around save(Around around);
     void delete(UUID id);
     Around update(Around around, UUID id);

    Around findAroundById(UUID id);

     List<Around> findAll();
    //public List<Around> findAroundByShift(Shift shift);
     List<Around> findAroundByCustomer(Customer customer);

     List<Around> getAroundsByCompany(UUID companyId);



}
