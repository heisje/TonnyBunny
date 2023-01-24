package com.tonnybunny.domain.review.service;


import com.tonnybunny.domain.review.dto.ReviewRequestDto;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReviewService {

	/**
	 * repository 에서 findReviewList() 를 수행한다.
	 *
	 * @return List<ReviewEntity>
	 */
	public List<ReviewEntity> getReviewList() {

		return new ArrayList<>();
	}


	/**
	 * repository 에서 findReview(review) 를 수행한다.
	 *
	 * @param reviewSeq
	 * @return ReviewEntity
	 */
	public ReviewEntity getReview(Long reviewSeq) {

		return (ReviewEntity) new Object();
	}


	/**
	 * repository 에서 insertReview(review) 를 수행한다. JPA 가 board 에 키 값을 넣어줌.
	 *
	 * @param reviewRequestDto
	 * @return review
	 */
	public Long createReview(ReviewRequestDto reviewRequestDto) {
		ReviewEntity review = reviewRequestDto.toEntity();

		return review.getSeq();
	}


	/**
	 * repository 에서 removeReview(review) 를 수행한다.
	 *
	 * @param reviewSeq
	 */
	public void deleteReview(Long reviewSeq) {

	}

}
