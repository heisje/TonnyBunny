package com.tonnybunny.domain.alert.repository;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertLogRepository extends JpaRepository<AlertLogEntity, Long> {

	// 알람 로그 목록 반환 By userSeq With pagenation
	Page<AlertLogEntity> findByUserSeq(Long userSeq, Pageable pageable);

}
