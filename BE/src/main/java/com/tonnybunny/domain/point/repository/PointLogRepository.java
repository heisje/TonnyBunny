package com.tonnybunny.domain.point.repository;


import com.tonnybunny.domain.point.entity.PointLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PointLogRepository extends JpaRepository<PointLogEntity, Long> {

	List<PointLogEntity> findAllByUserOrderByCreatedAtDesc(Long userSeq);

	PointLogEntity save(PointLogEntity pointLog);

}
