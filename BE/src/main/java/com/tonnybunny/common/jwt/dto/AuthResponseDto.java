package com.tonnybunny.common.jwt.dto;


import com.tonnybunny.common.jwt.entity.AuthEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponseDto {

	private String ACCESS_TOKEN;
	private String REFRESH_TOKEN;


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
