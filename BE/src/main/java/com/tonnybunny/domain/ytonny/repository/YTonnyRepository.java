package com.tonnybunny.domain.ytonny.repository;


import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YTonnyRepository extends JpaRepository<YTonnyEntity, Long> {

	// 예약통역 공고 목록 반환 With pagination OrderBy 생성일
	Page<YTonnyEntity> findByIsDeletedFalseOrderByCreatedAtDesc(Pageable pageable);

}
