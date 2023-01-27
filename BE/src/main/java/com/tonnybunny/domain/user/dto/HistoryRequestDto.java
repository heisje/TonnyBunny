package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * FIXME : HistoryRequestDto는 필터링이나 검색을 위한 용도, 나중에 삭제될 수 있음
 * seq              : 히스토리 Seq
 * clientSeq           : 고객
 * helperSeq           : 헬퍼
 * startDateTime    : 시작 시간
 * endDateTime      : 종료 시간
 * startLangCode    : 기존 언어
 * endLangCode      : 통역/번역 언어
 * taskCode         : 즉통/예통/번역 공통 코드
 * notiSeq          : 공고
 */
@Data
public class HistoryRequestDto {

	private Long seq;

	private Long clientSeq;

	private Long helperSeq;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String startLangCode;
	private String endLangCode;
	private String taskCode;

	private Long notiSeq;                   // 즉통, 예통의 경우 NotiSeq, 번역의 경우 QuotationSeq


	public HistoryEntity toEntity() {
		return (HistoryEntity) new Object();
	}

}
