package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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
@AllArgsConstructor
@Builder
public class YTonnyQuotationResponseDto {

	private Long seq;
	private YTonnyResponseDto yTonny;
	private UserResponseDto client;
	private UserResponseDto helper;

	private String title;
	private String content;

	private LocalDateTime startDateTime;
	private String estimateTime;
	private Integer unitPrice;

	private String startLangCode;
	private String endLangCode;
	private String quotationStateCode;

	private List<YTonnyQuotationImageResponseDto> yTonnyQuotationImageList;

}
