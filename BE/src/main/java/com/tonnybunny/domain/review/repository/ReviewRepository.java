package com.tonnybunny.domain.review.repository;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
