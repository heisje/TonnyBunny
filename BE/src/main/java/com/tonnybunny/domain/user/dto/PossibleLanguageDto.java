package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.PossibleLanguageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PossibleLanguageDto {

	private Long seq;       // 시퀀스
	private String name;    // 언어명
	private String value;   // 언어코드


	public static PossibleLanguageDto fromEntity(PossibleLanguageEntity possibleLanguage) {
		return PossibleLanguageDto.builder()
		                          .name(possibleLanguage.getLangName())
		                          .value(possibleLanguage.getLangCode())
		                          .build();
	}


	public static List<PossibleLanguageDto> fromEntityList(List<PossibleLanguageEntity> possibleLanguageList) {
		List<PossibleLanguageDto> result = new ArrayList<>();

		for (PossibleLanguageEntity possibleLanguage : possibleLanguageList) {
			result.add(PossibleLanguageDto.fromEntity(possibleLanguage));
		}

		return result;
	}


	public PossibleLanguageEntity toEntity() {
		return PossibleLanguageEntity.builder()
		                             .langCode(value)
		                             .langName(name)
		                             .build();
	}

}
