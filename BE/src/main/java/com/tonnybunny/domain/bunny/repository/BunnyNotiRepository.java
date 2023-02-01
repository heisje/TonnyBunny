package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BunnyNotiRepository extends JpaRepository<BunnyNotiEntity, Long> {

	List<BunnyNotiEntity> findAllByOrderByCreatedAtDesc();
	List<BunnyNotiEntity> findByStartLangCodeOrEndLangCodeOrderByCreatedAtDesc(String startLangCode, String endLangCode);
	List<BunnyNotiEntity> findByBunnySituCodeOrderByCreatedAtDesc(String bunnySituCode);

	List<BunnyNotiEntity> findByStartLangCodeAndBunnySituCodeOrEndLangCodeAndBunnySituCodeOrderByCreatedAtDesc(String startLangCode, String bunnySituCode, String endLangCode, String bunnySituCode2);
	Optional<BunnyNotiEntity> findById(Long bunnyNotiSeq);
	BunnyNotiEntity save(BunnyNotiEntity bunnyNoti);

}
