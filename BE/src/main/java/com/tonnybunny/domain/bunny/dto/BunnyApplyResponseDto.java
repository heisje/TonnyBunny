package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * seq              : 신청 seq
 * user             : 신청자 dto
 * bunnyNoti        : 번역 공고 dto
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyApplyResponseDto {

	private Long seq;
	private Long userSeq;
	private Long bunnySeq;
	private Integer estimatePrice;

	//	헬퍼카드용
	private Map<String, Object> helperInfo;
	private String profileImagePath;
	private String nickName;


	public static BunnyApplyResponseDto fromEntity(BunnyApplyEntity bunnyApply) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();

		// 값 매핑
		BunnyApplyResponseDto bunnyApplyResponseDto = modelMapper.map(bunnyApply, BunnyApplyResponseDto.class);

		return bunnyApplyResponseDto;
	}


	public static List<BunnyApplyResponseDto> fromEntityList(List<BunnyApplyEntity> bunnyNotiHelperList) {
		List<BunnyApplyResponseDto> result = new ArrayList<>();

		for (BunnyApplyEntity bunnyNotiHelper : bunnyNotiHelperList) {
			BunnyApplyResponseDto bunnyNotiHelperResponseDto = fromEntity(bunnyNotiHelper);
			result.add(bunnyNotiHelperResponseDto);
		}
		return result;
	}

}
