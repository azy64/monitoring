package com.tunaweza.monitoring.contract;

import com.tunaweza.monitoring.model.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionServiceInterface {

    public Subscription save(Subscription subscription);
    public void delete(UUID id);
    public Subscription update(Subscription subscription, UUID id);
    public List<Subscription> findAll();
    //List<Subscription> findActiveSubscriptions();
    //boolean isSubscriptionValid(UUID id);
}
