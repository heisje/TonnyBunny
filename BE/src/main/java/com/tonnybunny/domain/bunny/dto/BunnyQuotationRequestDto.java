package com.tonnybunny.domain.bunny.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * seq                      : 번역 견적서 seq
 * bunnyNotiSeq             : 번역 공고 seq
 * clientSeq                : 고객 dto
 * helperSeq                : 헬퍼 dto
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
public class BunnyQuotationRequestDto {

	private Long seq;
	private Long bunnySeq;
	private Long clientSeq;
	private Long helperSeq;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String title;
	private String content;
	private Integer totalPrice;
	private String startLangCode;
	private String endLangCode;
	private List<BunnyQuotationImageRequestDto> bunnyQuotationImageRequestDtoList;

}
