package com.tonnybunny.domain.ytonny.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * yTonnyNotiSeq           : 예약 통역 공고 seq
 * clientSeq               : 고객 seq
 * helperSeq               : 헬퍼 seq
 *
 * unitPrice               : 단가
 * title                   : 제목
 * content                 : 내용
 * startDate               : 시작 시간
 * endDate                 : 종료 시간
 *
 * startLangCode           : 시작 언어 코드
 * endLangCode             : 종료 언어 코드
 * quotationStateCode      : 견적서 상태 코드
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YTonnyQuotationRequestDto {

	private int page = 0; // offset
	private int size = 10; // limit

	private Long yTonnyQuotationSeq;

	private Long yTonnySeq;
	private Long clientSeq;
	private Long helperSeq;

	private String title;
	private String content;

	private Integer unitPrice;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime estimateTime;

	private String startLangCode;
	private String endLangCode;
	private String quotationStateCode;

}
