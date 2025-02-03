package com.tunaweza.monitoring.repository;

import com.tunaweza.monitoring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AroundRepository extends JpaRepository<Around, UUID> {
    public List<Around> findAroundByShift(Shift shift);

    public List<Around> findAroundByCustomer(Customer customer);
}
