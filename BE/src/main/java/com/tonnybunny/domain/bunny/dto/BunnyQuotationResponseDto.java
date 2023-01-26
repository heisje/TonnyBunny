package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                      : 번역 견적서 seq
 * bunnyNoti                : 번역 공고 seq
 * client                   : 고객 dto
 * helper                   : 헬퍼 dto
 * startDate                : 시작 시간
 * endDate                  : 종료 시간
 * title                    : 제목
 * content                  : 내용
 * totalPrice               : 최종 금액
 * bunnyQuotationStateCode  : 번역 견적서 상태 코드
 * startLangCode            : 시작 언어 코드
 * endLangCode              : 종료 언어 코드
 * bunnyQuotationImageList  : 번역 견적서 이미지 경로 리스트
 */
@Data
public class BunnyQuotationResponseDto {

	private Long seq;
	private BunnyNotiResponseDto bunnyNoti;
	private UserResponseDto client;
	private UserResponseDto helper;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String title;
	private String content;
	private Integer totalPrice;
	private String bunnyQuotationStateCode;
	private String startLangCode;
	private String endLangCode;
	private List<BunnyQuotationImageResponseDto> bunnyQuotationImageList;


	public static BunnyQuotationResponseDto fromEntity(BunnyQuotationEntity bunnyQuotation) {
		return new BunnyQuotationResponseDto();
	}


	public static List<BunnyQuotationResponseDto> fromEntityList(List<BunnyQuotationEntity> bunnyQuotationList) {
		List<BunnyQuotationResponseDto> result = new ArrayList<>();
		for (BunnyQuotationEntity bunnyQuotation : bunnyQuotationList) {
			BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
			result.add(bunnyQuotationResponseDto);
		}
		return result;
	}

}
