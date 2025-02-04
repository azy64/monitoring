package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.model.*;

import java.util.List;
import java.util.UUID;

public interface AroundServiceInterface {
    public Around save(Around around);
    public void delete(UUID id);
    public Around update(Around around, UUID id);

    Around findAroundById(UUID id);

    public List<Around> findAll();
    //public List<Around> findAroundByShift(Shift shift);
    public List<Around> findAroundByCustomer(Customer customer);


}
