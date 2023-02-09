package com.tonnybunny.domain.ytonny.repository;


import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YTonnyApplyRepository extends JpaRepository<YTonnyApplyEntity, Long> {

	Page<YTonnyApplyEntity> findByyTonnySeqOrderByCreatedAtDesc(Long yTonnySeq, Pageable pageable);
	Long countByyTonnySeq(Long yTonnySeq);

}
