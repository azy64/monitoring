package com.tunaweza.monitoring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{

}
