package com.tonnybunny.domain.alert.repository;


import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertSettingsRepository extends JpaRepository<AlertSettingsEntity, Long> {

	// 푸시 알람 설정 반환
	AlertSettingsEntity findByUserSeq(Long userSeq);

}
