package com.tonnybunny.domain.review.service;


import com.tonnybunny.domain.review.dto.ReviewRequestDto;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.review.repository.ReviewRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_ENTITY;
import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_USER;


@Service
@RequiredArgsConstructor
public class ReviewService {

	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;


	/**
	 * 유저 정보에서 Review List를 가져온다.
	 *
	 * @return List<ReviewEntity>
	 */
	public List<ReviewEntity> getReviewList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		return user.getReviewList();
	}


	/**
	 * repository 에서 findReview(review) 를 수행한다.
	 *
	 * @param reviewSeq
	 * @return ReviewEntity
	 */
	public ReviewEntity getReview(Long reviewSeq) {
		ReviewEntity review = reviewRepository.findById(reviewSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		return review;
	}


	/**
	 * createReview 생성
	 * Builder 사용
	 *
	 * @param reviewRequestDto
	 * @return review
	 */
	@Transactional
	public Long createReview(ReviewRequestDto reviewRequestDto) {
		UserEntity user = userRepository.findById(reviewRequestDto.getHelperSeq()).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		ReviewEntity review = ReviewEntity.builder()
		                                  .score(reviewRequestDto.getScore())
		                                  .comment(reviewRequestDto.getComment())
		                                  .user(user)
		                                  .build();
		reviewRepository.save(review);
		return review.getSeq();
	}


	/**
	 * repository 에서 removeReview(review) 를 수행한다.
	 *
	 * @param reviewSeq
	 */
	@Transactional
	public Boolean deleteReview(Long reviewSeq) {
		ReviewEntity review = reviewRepository.findById(reviewSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		review.deleteReview();
		reviewRepository.save(review);

		return true;
	}

}
