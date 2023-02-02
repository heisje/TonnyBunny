package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq          : seq
 * bunnyNoti    : 번역 공고 seq
 * imagePath    : 이미지 경로
 */
@Data
public class BunnyImageResponseDto {

	private Long seq;
	private Long bunnySeq;
	private String imagePath;


	public static BunnyImageResponseDto fromEntity(BunnyImageEntity bunnyNotiImage) {
		return new BunnyImageResponseDto();
	}


	public static List<BunnyImageResponseDto> fromEntityList(List<BunnyImageEntity> bunnyNotiImageList) {
		List<BunnyImageResponseDto> result = new ArrayList<>();
		for (BunnyImageEntity bunnyNotiImage : bunnyNotiImageList) {
			BunnyImageResponseDto bunnyNotiImageResponseDto = BunnyImageResponseDto.fromEntity(bunnyNotiImage);
			result.add(bunnyNotiImageResponseDto);
		}
		return result;
	}

}
