package com.tonnybunny.domain.review.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.review.dto.ReviewRequestDto;
import com.tonnybunny.domain.review.dto.ReviewResponseDto;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Api(tags = "리뷰 관련 API")
public class ReviewController {

	private final ReviewService reviewService;


	@GetMapping("/user/{userSeq}")
	@ApiOperation(value = "개별 유저의 리뷰 리스트를 조회합니다.")
	public ResponseEntity<ResultDto<List<ReviewResponseDto>>> getReviewList(@PathVariable("userSeq") Long userSeq) {

		List<ReviewEntity> reviewList = reviewService.getReviewList(userSeq);
		List<ReviewResponseDto> reviewResponseDtoList = ReviewResponseDto.fromEntityList(reviewList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewResponseDtoList));
	}


	@GetMapping("/{reviewSeq}")
	@ApiOperation(value = "리뷰를 조회합니다.")
	public ResponseEntity<ResultDto<?>> getReview(@PathVariable("reviewSeq") Long reviewSeq) {

		ReviewEntity review = reviewService.getReview(reviewSeq);
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.fromEntity(review);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewResponseDto));
	}


	@PostMapping
	@ApiOperation(value = "리뷰를 작성합니다.")
	public ResponseEntity<ResultDto<?>> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {

		Long reviewSeq = reviewService.createReview(reviewRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewSeq));
	}


	/**
	 * isDeleted True로 변경
	 *
	 * @param reviewSeq
	 * @return
	 */
	@DeleteMapping("/{reviewSeq}")
	@ApiOperation(value = "리뷰를 삭제합니다.")
	public ResponseEntity<ResultDto<Boolean>> deleteReview(@PathVariable("reviewSeq") Long reviewSeq) {

		reviewService.deleteReview(reviewSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
