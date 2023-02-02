package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
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
public class BunnyApplyResponseDto {

	private Long seq;
	private UserResponseDto user;
	private Long bunnySeq;
	private Integer estimatePrice;


	public static BunnyApplyResponseDto fromEntity(BunnyApplyEntity bunnyNotiHelperEntity) {
		return new BunnyApplyResponseDto();
	}


	public static List<BunnyApplyResponseDto> fromEntityList(List<BunnyApplyEntity> bunnyNotiHelperList) {
		List<BunnyApplyResponseDto> result = new ArrayList<>();

		for (BunnyApplyEntity bunnyNotiHelper : bunnyNotiHelperList) {
			BunnyApplyResponseDto bunnyNotiHelperResponseDto = fromEntity(bunnyNotiHelper);
			result.add(bunnyNotiHelperResponseDto);
		}
		return result;
	}

}
