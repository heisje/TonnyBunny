package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import lombok.Data;

import java.time.LocalDateTime;


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
public class YTonnyQuotationRequestDto {

	private Long yTonnySeq;
	private Long clientSeq;
	private Long helperSeq;

	private Integer unitPrice;
	private String title;
	private String content;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private String startLangCode;
	private String endLangCode;
	private String quotationStateCode;


	public YTonnyQuotationEntity toEntity() {
		return (YTonnyQuotationEntity) new Object();
	}

}
