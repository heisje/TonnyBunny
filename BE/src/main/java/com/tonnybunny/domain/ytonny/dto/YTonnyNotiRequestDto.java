package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * clientSeq                : 고객 seq
 *
 * title                    : 제목
 * estimateDate             : 요청 날짜
 * estimateStartTime        : 요청 시간
 * estimateTime             : 예상 소요 시간
 * estimatePrice            : 의뢰 금액 (null)
 * content                  : 내용 (null)
 *
 * startLangCode            : 시작 언어 코드
 * endLangCode              : 종료 언어 코드
 * tonnySituCode            : 통역상황 카테고리 코드 (null)
 */
@Data
public class YTonnyNotiRequestDto {

	private Long clientSeq;

	private String title;
	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;
	private String content;

	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;


	public YTonnyNotiEntity toEntity() {
		return (YTonnyNotiEntity) new Object();
	}

}
