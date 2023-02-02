package com.tonnybunny.common.auth.dto;


import com.tonnybunny.common.auth.entity.AuthEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponseDto {

	// 토큰 관련
	private String ACCESS_TOKEN;
	private String REFRESH_TOKEN;

	// 유저 관련
	private Long seq;
	private String email;
	private String nickName;
	private String profileImagePath;
	private Long point;
	private String userCode;


	public AuthResponseDto(String ACCESS_TOKEN, String REFRESH_TOKEN) {
		this.ACCESS_TOKEN = ACCESS_TOKEN;
		this.REFRESH_TOKEN = REFRESH_TOKEN;
	}


	public static AuthResponseDto fromEntity(AuthEntity auth) {
		return new AuthResponseDto();
	}


	public static List<AuthResponseDto> fromEntityList(List<AuthEntity> authList) {
		List<AuthResponseDto> result = new ArrayList<>();
		for (AuthEntity auth : authList) {
			AuthResponseDto authResponseDto = fromEntity(auth);
			result.add(authResponseDto);
		}
		return result;
	}

}
