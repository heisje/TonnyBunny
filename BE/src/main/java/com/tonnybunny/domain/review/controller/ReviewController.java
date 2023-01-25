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


	@GetMapping("/")
	@ApiOperation(value = "리뷰 리스트를 조회합니다.", notes = "")
	public ResponseEntity<?> getReviewList() {

		List<ReviewEntity> reviewList = reviewService.getReviewList();
		List<ReviewResponseDto> reviewResponseDtoList = ReviewResponseDto.fromEntityList(reviewList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewResponseDtoList));
	}


	@GetMapping("/{reviewSeq}")
	@ApiOperation(value = "리뷰를 조회합니다.", notes = "")
	public ResponseEntity<?> getReview(@PathVariable Long reviewSeq) {

		ReviewEntity review = reviewService.getReview(reviewSeq);
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.fromEntity(review);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewResponseDto));
	}


	@PostMapping("/")
	@ApiOperation(value = "리뷰를 작성합니다.", notes = "")
	public ResponseEntity<?> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {

		Long reviewSeq = reviewService.createReview(reviewRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reviewSeq));
	}


	@DeleteMapping("/{reviewSeq}")
	@ApiOperation(value = "리뷰를 삭제합니다.", notes = "")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewSeq) {

		reviewService.deleteReview(reviewSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
