package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyImageEntity;
import lombok.Data;


/**
 * bunnyNotiSeq : 번역 공고 seq
 * imagePath    : 이미지 경로
 */
@Data
public class BunnyImageRequestDto {

	private Long bunnySeq;
	private String imagePath;


	public BunnyImageEntity toEntity() {
		return (BunnyImageEntity) new Object();
	}

}
