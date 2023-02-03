package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BunnyQuotationRepository extends JpaRepository<BunnyQuotationEntity, Long> {

	List<BunnyQuotationEntity> findByBunnySeq(Long bunnySeq);

}
