package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class HelperInfoImageResponseDto {

	private Long seq;
	private Long helperInfoSeq;
	private String imagePath;
	private Boolean isDeleted;


	public static HelperInfoImageResponseDto fromEntity(HelperInfoImageEntity helperInfoImage) {
		return new HelperInfoImageResponseDto();
	}


	public static List<HelperInfoImageResponseDto> fromEntityList(List<HelperInfoImageEntity> helperInfoImageList) {
		List<HelperInfoImageResponseDto> result = new ArrayList<>();
		for (HelperInfoImageEntity helperInfoImage : helperInfoImageList) {
			HelperInfoImageResponseDto helperInfoImageResponseDto = HelperInfoImageResponseDto.fromEntity(helperInfoImage);
			result.add(helperInfoImageResponseDto);
		}
		return result;
	}

}
