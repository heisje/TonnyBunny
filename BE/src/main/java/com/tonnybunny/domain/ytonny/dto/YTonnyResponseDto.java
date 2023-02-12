package com.tonnybunny.domain.ytonny.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YTonnyResponseDto {

	private Long seq;

	private UserResponseDto client;
	private UserResponseDto helper;

	private String sessionName;

	private String title;
	private String content;

	private LocalDateTime startDateTime;
	private String estimateTime;
	private Integer estimatePrice;
	private Integer unitPrice;
	private Long yTonnyApplySeq;
	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String taskCode;
	private String taskStateCode;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private Boolean isDeleted;


	public static YTonnyResponseDto fromEntity(YTonnyEntity yTonnyEntity) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		return modelMapper.map(yTonnyEntity, YTonnyResponseDto.class);
	}


	public static List<YTonnyResponseDto> fromEntityList(List<YTonnyEntity> yTonnyEntityList) {
		return yTonnyEntityList.stream().map(m -> fromEntity(m)).collect(Collectors.toList());
	}

}
