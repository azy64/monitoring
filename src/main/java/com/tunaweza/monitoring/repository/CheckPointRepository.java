package com.tunaweza.monitoring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunaweza.monitoring.model.CheckPoint;

public interface CheckPointRepository  extends JpaRepository<CheckPoint, UUID>{

}
