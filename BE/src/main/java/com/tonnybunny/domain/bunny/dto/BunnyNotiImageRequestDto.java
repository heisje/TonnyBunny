package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiImageEntity;
import lombok.Data;


/**
 * bunnyNotiSeq : 번역 공고 seq
 * imagePath    : 이미지 경로
 */
@Data
public class BunnyNotiImageRequestDto {

	private Long bunnyNotiSeq;
	private String imagePath;


	public BunnyNotiImageEntity toEntity() {
		return (BunnyNotiImageEntity) new Object();
	}

}
