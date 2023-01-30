package com.tonnybunny.common.jwt.dto;


import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {

	private String ACCESS_TOKEN;
	private String REFRESH_TOKEN;

}
