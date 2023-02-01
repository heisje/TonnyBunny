package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq          : seq
 * bunnyNoti    : 번역 공고 seq
 * imagePath    : 이미지 경로
 */
@Data
public class BunnyNotiImageResponseDto {

	private Long seq;
	private Long bunnyNotiSeq;
	private String imagePath;


	public static BunnyNotiImageResponseDto fromEntity(BunnyNotiImageEntity bunnyNotiImage) {
		return new BunnyNotiImageResponseDto();
	}


	public static List<BunnyNotiImageResponseDto> fromEntityList(List<BunnyNotiImageEntity> bunnyNotiImageList) {
		List<BunnyNotiImageResponseDto> result = new ArrayList<>();
		for (BunnyNotiImageEntity bunnyNotiImage : bunnyNotiImageList) {
			BunnyNotiImageResponseDto bunnyNotiImageResponseDto = BunnyNotiImageResponseDto.fromEntity(bunnyNotiImage);
			result.add(bunnyNotiImageResponseDto);
		}
		return result;
	}

}
