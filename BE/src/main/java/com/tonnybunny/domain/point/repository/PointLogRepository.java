package com.tonnybunny.domain.point.repository;


import com.tonnybunny.domain.point.entity.PointLogEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PointLogRepository extends JpaRepository<PointLogEntity, Long> {

	List<PointLogEntity> findAllByUserOrderByCreatedAtDesc(UserEntity user);

	//	PointLogEntity save(PointLogEntity pointLog);

}
