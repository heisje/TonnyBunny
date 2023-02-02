package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq              : 신청 seq
 * user             : 신청자 dto
 * bunnyNoti        : 번역 공고 dto
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyNotiHelperResponseDto {

	private Long seq;
	private UserResponseDto user;
	private Long bunnyNotiSeq;
	private Integer estimatePrice;


	public static BunnyNotiHelperResponseDto fromEntity(BunnyNotiHelperEntity bunnyNotiHelperEntity) {
		return new BunnyNotiHelperResponseDto();
	}


	public static List<BunnyNotiHelperResponseDto> fromEntityList(List<BunnyNotiHelperEntity> bunnyNotiHelperList) {
		List<BunnyNotiHelperResponseDto> result = new ArrayList<>();

		for (BunnyNotiHelperEntity bunnyNotiHelper : bunnyNotiHelperList) {
			BunnyNotiHelperResponseDto bunnyNotiHelperResponseDto = fromEntity(bunnyNotiHelper);
			result.add(bunnyNotiHelperResponseDto);
		}
		return result;
	}

}
