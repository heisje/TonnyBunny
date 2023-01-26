package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 번역 공고 seq
 * user                 : 작성자 seq                   : user response Dto
 * helperSeq            : 담당자 seq
 * title                : 제목
 * content              : 내용
 * startDate            : 시작일
 * endDate              : 마감일
 * estimatePrice        : 의뢰가격
 *
 * startLangCode        : 시작 언어 코드
 * endLangCode          : 종료 언어 코드
 * bunnySituCode        : 번역상황 카테고리 코드
 * bunnyStateCode       : 번역공고 상태 코드
 *
 * bunnyNotiHelperList  : 지원한 헬퍼들의 신청 리스트
 * bunnyNotiImageList   : 번역 공고 이미지 경로 리스트
 * bunnyQuotationList   : 번역 견적서 리스트
 */
@Data
public class BunnyNotiResponseDto {

	private Long seq;
	private UserResponseDto userDto;
	private Long helperSeq;
	private String title;
	private String content;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer estimatePrice;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	private String bunnyStateCode;

	private List<BunnyNotiHelperResponseDto> bunnyNotiHelperList;
	private List<BunnyNotiImageResponseDto> bunnyNotiImageList;
	private List<BunnyQuotationResponseDto> bunnyQuotationList;


	public static BunnyNotiResponseDto fromEntity(BunnyNotiEntity bunnyNotiSeq) {
		return new BunnyNotiResponseDto();
	}


	public static List<BunnyNotiResponseDto> fromEntityList(List<BunnyNotiEntity> bunnyNotiList) {
		List<BunnyNotiResponseDto> result = new ArrayList<>();

		for (BunnyNotiEntity bunnyNoti : bunnyNotiList) {
			BunnyNotiResponseDto bunnyNotiResponseDto = fromEntity(bunnyNoti);
			result.add(bunnyNotiResponseDto);
		}

		return result;

	}

}
