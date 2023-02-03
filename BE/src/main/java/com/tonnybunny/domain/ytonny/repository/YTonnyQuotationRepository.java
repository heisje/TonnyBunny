package com.tonnybunny.domain.ytonny.repository;


import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YTonnyQuotationRepository extends JpaRepository<YTonnyQuotationEntity, Long> {

	Page<YTonnyQuotationEntity> findByyTonnySeqOrderByCreatedAtDesc(Long yTonnySeq, Pageable pageable);

}
