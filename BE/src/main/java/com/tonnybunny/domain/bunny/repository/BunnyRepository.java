package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BunnyRepository extends JpaRepository<BunnyEntity, Long> {

	List<BunnyEntity> findAllByOrderByCreatedAtDesc();
	List<BunnyEntity> findByStartLangCodeOrEndLangCodeOrderByCreatedAtDesc(String startLangCode, String endLangCode);
	List<BunnyEntity> findByBunnySituCodeOrderByCreatedAtDesc(String bunnySituCode);

	List<BunnyEntity> findByStartLangCodeAndBunnySituCodeOrEndLangCodeAndBunnySituCodeOrderByCreatedAtDesc(String startLangCode, String bunnySituCode, String endLangCode, String bunnySituCode2);
	Optional<BunnyEntity> findById(Long bunnySeq);

}
