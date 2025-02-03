package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.model.*;
import com.tunaweza.monitoring.repository.AroundRepository;
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
    @Override
    public Around save(Around around) {return aroundRepository.save(around);}

    @Override
    public void delete(UUID id) {
        Around around = aroundRepository.findById(id).orElse(null);
        if(around!=null) aroundRepository.delete(around);
        else
            throw new UnsupportedOperationException("Id:"+id+" was not found!");
    }

    @Override
    public Around update(Around around, UUID id) {
        Around previousAround = aroundRepository.findById(id).orElse(null);
        if(previousAround!=null){
            previousAround.setAddress(around.getAddress());
            previousAround.setControlPoints(around.getControlPoints());
            previousAround.setLabel(around.getLabel());
            previousAround.setCustomer(around.getCustomer());

            return aroundRepository.save(previousAround);
        }
        else
            throw new UnsupportedOperationException("The around with the id:"+id+" does not exist");
    }

    @Override
    public Around findAroundById(UUID id) {
        Around around = aroundRepository.findById(id).orElse(null);
        if(around!=null) return around;
        else
            throw new UnsupportedOperationException("Id:"+id+" was not found!");

    }

    @Override
    public List<Around> findAll() {
        return aroundRepository.findAll();
    }

    @Override
    public List<Around> findAroundByShift(Shift shift) {
        return aroundRepository.findAroundByShift(shift);

    }



    @Override
    public List<Around> findAroundByCustomer(Customer customer) {
        return aroundRepository.findAroundByCustomer(customer);
    }

}
