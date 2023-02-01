package com.tonnybunny.domain.alert.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class AlertSettingsDto {

	private Long alertSettingsSeq;

	private Long userSeq;

	private Boolean isAll;
	private Boolean isTonnyBunny;
	private Boolean isCommunity;
	private Boolean isChat;

}
