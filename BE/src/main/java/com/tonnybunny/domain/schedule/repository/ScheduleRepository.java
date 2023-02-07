package com.tonnybunny.domain.schedule.repository;


import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

}
