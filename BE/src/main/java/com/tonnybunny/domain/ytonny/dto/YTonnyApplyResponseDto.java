package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * seq             : 신청 seq
 * yTonnyNoti      : 예약 통역 공고
 * helper          : 헬퍼 유저
 * totalPrice      : 견적 금액
 */
@Data
@AllArgsConstructor
@Builder
public class YTonnyApplyResponseDto {

	private Long seq;

	private YTonnyResponseDto yTonnyNoti;
	private UserResponseDto helper;
	private Integer totalPrice;

}
