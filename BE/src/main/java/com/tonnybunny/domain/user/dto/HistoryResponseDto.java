package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.review.dto.ReviewResponseDto;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq              : 히스토리 Seq
 * client           : 고객
 * helper           : 헬퍼
 * content          : 공고 내용
 * startDateTime    : 시작 시간
 * endDateTime      : 종료 시간
 * startLangCode    : 기존 언어
 * endLangCode      : 통역/번역 언어
 * taskCode         : 즉통/예통/번역 공통 코드
 * notiSeq          : 공고
 * review           : 리뷰
 * totalTime        : 총 소요 시간 (즉통, 예통만 )
 * unitPrice        : 단가 (즉통, 예통만)
 * recordVideoPath  : 녹화 파일 (즉통, 예통만)
 * tonnySituCode    : 통역 상황 코드 (즉통, 예통만)
 * title            : 공고 제목 (예통, 번역만)
 */
@Data
public class HistoryResponseDto {

	private Long seq;

	private UserResponseDto client;

	private UserResponseDto helper;

	private String content;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String startLangCode;
	private String endLangCode;
	private String taskCode;

	private Long notiSeq;                   // 즉통, 예통의 경우 NotiSeq, 번역의 경우 QuotationSeq

	private ReviewResponseDto review;

	/************************ 즉통 & 예통 **************************/

	private LocalTime totalTime;
	private Integer unitPrice;
	private String recordVideoPath;
	private String tonnySituCode = "0";       // 디폴트 "일상" 코드 넣기

	/************************ 예통 & 번역 **************************/

	private String title;


	public static HistoryResponseDto fromEntity(HistoryEntity history) {
		return new HistoryResponseDto();
	}


	public static List<HistoryResponseDto> fromEntityList(List<HistoryEntity> historyList) {
		List<HistoryResponseDto> result = new ArrayList<>();
		for (HistoryEntity history : historyList) {
			HistoryResponseDto historyResponseDto = fromEntity(history);
			result.add(historyResponseDto);
		}
		return result;
	}

}
