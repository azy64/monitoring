package com.tunaweza.monitoring.contract;

import java.util.List;


public interface GenericServiceInterface<T> {

    public T save(T object);
    public T delete(Long id);
    public T update(T object, Long id);
    public T findAgent(Long id);
    public List<T> findAll();
    
}
