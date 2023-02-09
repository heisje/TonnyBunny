package com.tonnybunny.domain.review.dto;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import lombok.Data;


/**
 * userSeq             : 작성자 Seq
 * score               : 별점
 * comment             : 내용
 */
@Data
public class ReviewRequestDto {

	private Long historySeq;
	private Long helperSeq;
	private Integer score;
	private String comment;


	public ReviewEntity toEntity() {
		return (ReviewEntity) new Object();
	}

}
