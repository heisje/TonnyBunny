package com.tonnybunny.domain.alert.repository;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends JpaRepository<AlertLogEntity, Long> {
}
