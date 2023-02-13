package com.tonnybunny.domain.schedule.repository;


import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

	List<ScheduleEntity> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
