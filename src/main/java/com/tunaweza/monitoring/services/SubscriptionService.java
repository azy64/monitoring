package com.tunaweza.monitoring.services;

import com.tunaweza.monitoring.contract.SubscriptionServiceInterface;
import com.tunaweza.monitoring.model.Subscription;
import com.tunaweza.monitoring.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionService implements SubscriptionServiceInterface {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(UUID id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription != null) subscriptionRepository.delete(subscription);
        else
            throw new UnsupportedOperationException("Id:" + id + " was not found!");

    }

    @Override
    public Subscription update(Subscription subscription, UUID id) {
        Subscription previousSubscription = subscriptionRepository.findById(id).orElse(null);
        if (previousSubscription != null) {
            previousSubscription.setAmount(subscription.getAmount());
            previousSubscription.setSubscribeDate(subscription.getSubscribeDate());
            previousSubscription.setSubscribeEndDate(subscription.getSubscribeEndDate());
            previousSubscription.setSubscribeState(subscription.getSubscribeState());
            previousSubscription.setCompany(subscription.getCompany());
            return subscriptionRepository.save(previousSubscription);
        } else
            throw new UnsupportedOperationException("The subscription with the id:" + id + " does not exist");
    }


    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

   /* @Override
    public List<Subscription> findActiveSubscriptions() {
        return subscriptionRepository.findActiveSubscriptions();
    }
    
    @Override
    public boolean isSubscriptionValid(UUID id) {
       return subscriptionRepository.findById(id)
                .map(subscription -> subscription.getSubscribeEndDate().isAfter(subscription.getSubscribeDate()))
                .orElse(false);

        return true;
    }
 */
}
