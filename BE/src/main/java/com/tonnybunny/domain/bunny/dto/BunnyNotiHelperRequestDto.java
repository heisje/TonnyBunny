package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import com.tonnybunny.domain.user.dto.UserRequestDto;
import lombok.Data;


/**
 * user             : 신청자 dto       : dto를 주는게 맞나? erd에서 해당 필드는 신청자의 헬퍼정보도 조회하기 위한 seq임
 * bunnyNoti        : 번역 공고 seq
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyNotiHelperRequestDto {

	private UserRequestDto user;
	private Long bunnyNotiSeq;
	private Integer estimatePrice;


	public BunnyNotiHelperEntity toEntity() {
		return (BunnyNotiHelperEntity) new Object();
	}

}
