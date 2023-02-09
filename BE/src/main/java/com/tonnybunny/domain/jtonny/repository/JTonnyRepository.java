package com.tonnybunny.domain.jtonny.repository;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JTonnyRepository extends JpaRepository<JTonnyEntity, Long> {
	
}
