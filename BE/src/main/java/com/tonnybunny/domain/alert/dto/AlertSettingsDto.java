package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Data
public class AlertSettingsDto {

	@Autowired
	private static final ModelMapper modelMapper = new ModelMapper();

	private Long userSeq;

	private Boolean isAll;
	private Boolean isTonnyBunny;
	private Boolean isCommunity;
	private Boolean isChat;


	public static AlertSettingsDto fromEntity(AlertSettingsEntity alertSettings) {
		return modelMapper.map(alertSettings, AlertSettingsDto.class);
	}

}
