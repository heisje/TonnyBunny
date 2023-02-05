package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                      : 번역 견적서 seq
 * bunnyNoti                : 번역 공고 seq
 * client                   : 고객 dto
 * helper                   : 헬퍼 dto
 * startDate                : 시작 시간
 * endDate                  : 종료 시간
 * title                    : 제목
 * content                  : 내용
 * totalPrice               : 최종 금액
 * bunnyQuotationStateCode  : 번역 견적서 상태 코드
 * startLangCode            : 시작 언어 코드
 * endLangCode              : 종료 언어 코드
 * bunnyQuotationImageList  : 번역 견적서 이미지 경로 리스트
 */
@Data
public class BunnyQuotationResponseDto {

	private Long seq;
	private Long bunnySeq;
	private Long clientSeq;
	private Long helperSeq;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String title;
	private String content;
	private Integer totalPrice;
	private String startLangCode;
	private String endLangCode;
	private List<BunnyQuotationImageResponseDto> bunnyQuotationImageList;


	public static BunnyQuotationResponseDto fromEntity(BunnyQuotationEntity bunnyQuotation) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();

		BunnyQuotationResponseDto bunnyQuotationResponseDto = modelMapper.map(bunnyQuotation, BunnyQuotationResponseDto.class);

		return bunnyQuotationResponseDto;
	}


	public static List<BunnyQuotationResponseDto> fromEntityList(List<BunnyQuotationEntity> bunnyQuotationList) {
		List<BunnyQuotationResponseDto> result = new ArrayList<>();
		for (BunnyQuotationEntity bunnyQuotation : bunnyQuotationList) {
			BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
			result.add(bunnyQuotationResponseDto);
		}
		return result;
	}

}
