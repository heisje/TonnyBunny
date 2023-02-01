package com.tonnybunny.domain.ytonny.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.common.dto.LangCodeEnum;
import com.tonnybunny.common.dto.TaskCodeEnum;
import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.common.dto.TonnySituCodeEnum;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * seq                      : 공고 seq
 *
 * client                   : 고객
 * helper                   : 헬퍼 (null)
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
 * taskCode                 : 의뢰 코드
 * taskStateCode            : 의뢰 상태 코드
 *
 * yTonnyNotiHelperList     : 헬퍼의 신청 목록
 *
 * yTonnyQuotationList      : 헬퍼의 견적서 목록
 */
@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YTonnyResponseDto {

	private Long seq;

	private UserResponseDto client;
	private UserResponseDto helper;

	private String title;
	private String content;

	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;

	private LangCodeEnum startLangCode;
	private LangCodeEnum endLangCode;
	private TonnySituCodeEnum tonnySituCode;
	private TaskCodeEnum taskCode;
	private TaskStateCodeEnum taskStateCode;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private List<YTonnyApplyResponseDto> yTonnyApplyList;
	private List<YTonnyQuotationResponseDto> yTonnyQuotationList;

}
