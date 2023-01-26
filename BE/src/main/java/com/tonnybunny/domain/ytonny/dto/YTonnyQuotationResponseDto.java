package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                     : 예약 통역 견적서 seq
 * yTonnyNoti              : 예약 통역 공고
 * client                  : 고객
 * helperD                 : 헬퍼
 *
 * unitPrice               : 단가
 * title                   : 제목
 * content                 : 내용
 * estimateDate            : 요청 날짜
 * estimateStartTime       : 요청 시작 시간
 * estimateTime            : 예상 소요 시간
 * yTonnyQuotationImageList: 견적서 이미지 리스트
 *
 * startLangCode           : 시작 언어 코드
 * endLangCode             : 종료 언어 코드
 * quotationStateCode      : 견적서 상태 코드
 */
@Data
public class YTonnyQuotationResponseDto {

	private Long seq;
	private YTonnyNotiResponseDto yTonnyNoti;
	private UserResponseDto client;
	private UserResponseDto helper;

	private Integer unitPrice;
	private String title;
	private String content;
	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private List<YTonnyQuotationImageResponseDto> yTonnyQuotationImageList;

	private String startLangCode;
	private String endLangCode;
	private String quotationStateCode;


	public static YTonnyQuotationResponseDto fromEntity(YTonnyQuotationEntity yTonnyQuotation) {
		return new YTonnyQuotationResponseDto();
	}


	public static List<YTonnyQuotationResponseDto> fromEntityList(List<YTonnyQuotationEntity> yTonnyQuotationList) {
		List<YTonnyQuotationResponseDto> result = new ArrayList<>();
		for (YTonnyQuotationEntity yTonnyQuatation : yTonnyQuotationList) {
			YTonnyQuotationResponseDto yTonnyQuotationResponseDto = YTonnyQuotationResponseDto.fromEntity(yTonnyQuatation);
			result.add(yTonnyQuotationResponseDto);
		}
		return result;
	}

}
