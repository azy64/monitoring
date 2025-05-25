package com.tunaweza.monitoring.repository;

import com.tunaweza.monitoring.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    //public List<Subscription> findActiveSubscriptions();
    //public boolean isSubscriptionValid(UUID id);

}
