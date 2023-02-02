package com.tonnybunny.domain.ytonny.repository;


import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface YTonnyApplyRepository extends JpaRepository<YTonnyApplyEntity, Long> {

	// 예약통역 신청 목록 리스트 반환 By 예약통역 공고 seq
	List<YTonnyApplyEntity> findByyTonnySeq(Long yTonnySeq);

}
