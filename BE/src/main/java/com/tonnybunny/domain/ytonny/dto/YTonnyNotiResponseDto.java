package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                      : 공고 seq
 *
 * clientDto                : 고객 seq
 * helperSeq                : 헬퍼 seq (null)
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
 * yTonnyNotiHelperListDto  : 헬퍼의 신청 목록
 *
 * yTonnyQuotationListDto   : 헬퍼의 견적서 목록
 */
@Data
public class YTonnyNotiResponseDto {

	private Long seq;

	private UserResponseDto clientDto;
	private Long helperSeq;

	private String title;
	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;
	private String content;

	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String taskCode;
	private String taskStateCode;

	private List<YTonnyNotiHelperResponseDto> yTonnyNotiHelperListDto;

	private List<YTonnyQuotationResponseDto> yTonnyQuotationListDto;


	public static YTonnyNotiResponseDto fromEntity(YTonnyNotiEntity yTonnyNoti) {
		return new YTonnyNotiResponseDto();
	}


	public static List<YTonnyNotiResponseDto> fromEntityList(
		List<YTonnyNotiEntity> yTonnyNotiList) {
		List<YTonnyNotiResponseDto> result = new ArrayList<>();
		for (YTonnyNotiEntity yTonnyNoti : yTonnyNotiList) {
			YTonnyNotiResponseDto yTonnyNotiResponseDto = YTonnyNotiResponseDto.fromEntity(
				yTonnyNoti);
			result.add(yTonnyNotiResponseDto);
		}
		return result;
	}

}
