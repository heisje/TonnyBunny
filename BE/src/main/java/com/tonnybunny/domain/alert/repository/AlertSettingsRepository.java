package com.tonnybunny.domain.alert.repository;


import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AlertSettingsRepository extends JpaRepository<AlertSettingsEntity, Long> {

	// 푸시 알람 설정 반환
	Optional<AlertSettingsEntity> findByUserSeq(Long userSeq);

}
