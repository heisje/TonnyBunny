package com.tonnybunny.domain.review.dto;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq              : 리뷰 번호
 * user             : 작성자 정보
 * score            : 별점
 * comment          : 내용
 */
@Data
@Builder
public class ReviewResponseDto {

	private Long seq;

	private UserResponseDto user;

	private Float score;

	private String comment;


	public static ReviewResponseDto fromEntity(ReviewEntity review) {

		return ReviewResponseDto.builder()
		                        .seq(review.getSeq())
		                        .user(UserResponseDto.fromEntity(review.getUser()))
		                        .comment(review.getComment())
		                        .score(review.getScore())
		                        .build();
	}


	public static List<ReviewResponseDto> fromEntityList(List<ReviewEntity> reviewList) {
		List<ReviewResponseDto> result = new ArrayList<>();

		for (ReviewEntity review : reviewList) {
			ReviewResponseDto reviewResponseDto = fromEntity(review);
			result.add(reviewResponseDto);
		}

		return result;

	}

}
