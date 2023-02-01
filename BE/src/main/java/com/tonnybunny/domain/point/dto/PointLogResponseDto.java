package com.tonnybunny.domain.point.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 포인트 로그 Seq
 * amount               : 포인트 양
 * pointRequestType     : 충전/출금/거래 공통 코드
 * userSeq              : 포인트 사용자 Seq
 * created_at           : 포인트 로그 생성 날짜
 */
@Data
public class PointLogResponseDto {

	private Long seq;

	private Integer amount;

	private String pointRequestType;

	private Long userSeq;

	private LocalDateTime createdAt;


	public static PointLogResponseDto fromEntity(PointLogEntity pointLog) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		// 자동 매핑되지 않는 필드를 설정
		modelMapper.typeMap(PointLogEntity.class, PointLogResponseDto.class).addMappings(mapper -> {
			// 매핑 필드의 타입은 같으나, 필드명이 다른 경우
			mapper.map(PointLogEntity::getTypeCode, PointLogResponseDto::setPointRequestType);
		});
		// UserEntity와 Long(userSeq)간 매핑은 자동으로 해준다! 심지어 필드명도 다른데(user <-> userSeq) 해줌
		PointLogResponseDto pointLogResponseDto = modelMapper.map(pointLog, PointLogResponseDto.class);
		return pointLogResponseDto;
	}


	public static List<PointLogResponseDto> fromEntityList(List<PointLogEntity> pointLogList) {
		List<PointLogResponseDto> result = new ArrayList<>();
		for (PointLogEntity pointLog : pointLogList) {
			PointLogResponseDto pointLogResponseDto = fromEntity(pointLog);
			result.add(pointLogResponseDto);
		}
		return result;
	}

}
