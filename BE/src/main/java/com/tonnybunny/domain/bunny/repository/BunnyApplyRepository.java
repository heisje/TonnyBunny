package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BunnyApplyRepository extends JpaRepository<BunnyApplyEntity, Long> {

	List<BunnyApplyEntity> findByBunnySeq(Long bunnySeq);

}
