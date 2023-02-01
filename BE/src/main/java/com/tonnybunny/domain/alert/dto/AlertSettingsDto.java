package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertSettingsDto {

	public static ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ModelMapper modelBeanMapper;

	private Long alertSettingsSeq;
	private Long userSeq;

	private Boolean isAll;
	private Boolean isTonnyBunny;
	private Boolean isCommunity;
	private Boolean isChat;


	public static AlertSettingsDto fromEntity(AlertSettingsEntity alertSettings) {
		return modelMapper.map(alertSettings, AlertSettingsDto.class);
	}


	@PostConstruct
	private void initialize() {
		modelMapper = this.modelBeanMapper;
	}

}
