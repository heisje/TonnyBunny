package com.tonnybunny.domain.alert.dto;


import lombok.Data;


@Data
public class AlertSettingsDto {

	private Boolean tonnyAlert;
	private Boolean bunnyAlert;
	private Boolean communityAlert;
	private Boolean messageAlert;

}
