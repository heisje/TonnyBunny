package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@RequiredArgsConstructor
public class AlertSettingsDto {

	private Long alertSettingsSeq;
	private Long userSeq;

	private Boolean isAll;
	private Boolean isTonnyBunny;
	private Boolean isCommunity;
	private Boolean isChat;


	public static AlertSettingsDto fromEntity(AlertSettingsEntity alertSettings) {
		ModelMapper mapper = ModelMapperFactory.getMapper();
		return mapper.map(alertSettings, AlertSettingsDto.class);
	}

}
