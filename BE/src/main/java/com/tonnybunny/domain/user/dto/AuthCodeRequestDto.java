package com.tonnybunny.domain.user.dto;


import lombok.Data;


@Data
public class AuthCodeRequestDto {

	private String authCode;
	private String phoneNumber;

}
