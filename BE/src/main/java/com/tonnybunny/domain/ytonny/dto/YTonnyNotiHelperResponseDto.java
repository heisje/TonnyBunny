package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq             : 신청 seq
 * yTonnyNoti      : 예약 통역 공고
 * helper          : 헬퍼 유저
 * totalPrice      : 견적 금액
 */
@Data
public class YTonnyNotiHelperResponseDto {

	private Long seq;
	private YTonnyNotiResponseDto yTonnyNoti;
	private UserResponseDto helper;
	private Integer totalPrice;


	public static YTonnyNotiHelperResponseDto fromEntity(YTonnyNotiHelperEntity yTonnyNotiHelper) {
		return new YTonnyNotiHelperResponseDto();
	}


	public static List<YTonnyNotiHelperResponseDto> fromEntityList(
		List<YTonnyNotiHelperEntity> yTonnyNotiHelperList) {
		List<YTonnyNotiHelperResponseDto> result = new ArrayList<>();
		for (YTonnyNotiHelperEntity yTonnyNotiHelper : yTonnyNotiHelperList) {
			YTonnyNotiHelperResponseDto yTonnyNotiHelperResponseDto = YTonnyNotiHelperResponseDto.fromEntity(
				yTonnyNotiHelper);
			result.add(yTonnyNotiHelperResponseDto);
		}
		return result;
	}

}
