package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * seq                  : 번역 공고 seq
 * client               : 작성자 seq                   : user response Dto
 * helper               : 담당자 seq
 * title                : 제목
 * content              : 내용
 * startDate            : 시작일
 * endDate              : 마감일
 * estimatePrice        : 의뢰가격
 *
 * startLangCode        : 시작 언어 코드
 * endLangCode          : 종료 언어 코드
 * bunnySituCode        : 번역상황 카테고리 코드
 * taskStateCode       : 번역공고 상태 코드
 *
 * bunnyNotiHelperList  : 지원한 헬퍼들의 신청 리스트
 * bunnyNotiImageList   : 번역 공고 이미지 경로 리스트
 * bunnyQuotationList   : 번역 견적서 리스트
 */
@Data
public class BunnyResponseDto {

	private Long seq;
	private Map<String, String> client = new HashMap<>();
	private Map<String, String> helper = new HashMap<>();
	private String title;
	private String content;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Integer estimatePrice;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	private String taskStateCode;
	private String taskCode;

	private List<BunnyApplyResponseDto> bunnyApplyList;
	private List<BunnyImageResponseDto> bunnyImageList;
	private List<BunnyQuotationResponseDto> bunnyQuotationList;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public static BunnyResponseDto fromEntity(BunnyEntity bunnyNoti) {

		ModelMapper modelMapper = ModelMapperFactory.getMapper();

		BunnyResponseDto bunnyNotiResponseDto = modelMapper.map(bunnyNoti, BunnyResponseDto.class);

		return bunnyNotiResponseDto;
	}


	public static List<BunnyResponseDto> fromEntityList(List<BunnyEntity> bunnyNotiList) {
		List<BunnyResponseDto> result = new ArrayList<>();

		for (BunnyEntity bunnyNoti : bunnyNotiList) {
			BunnyResponseDto bunnyNotiResponseDto = fromEntity(bunnyNoti);
			result.add(bunnyNotiResponseDto);
		}

		return result;

	}

}
