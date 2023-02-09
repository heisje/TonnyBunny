package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * seq             : 신청 seq
 * yTonnyNoti      : 예약 통역 공고
 * helper          : 헬퍼 유저
 * unitPrice      : 견적 금액
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YTonnyApplyResponseDto {

	private Long seq;

	private Long yTonnySeq;
	private UserResponseDto helper; // nick 까지 넘겨야하니까

	private Integer unitPrice;


	public static YTonnyApplyResponseDto fromEntity(YTonnyApplyEntity yTonnyApply) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		return modelMapper.map(yTonnyApply, YTonnyApplyResponseDto.class);
	}


	public static List<YTonnyApplyResponseDto> fromEntityList(List<YTonnyApplyEntity> yTonnyApplyList) {
		return yTonnyApplyList.stream().map(m -> fromEntity(m)).collect(Collectors.toList());
	}

}
