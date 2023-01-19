package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserResponseDto {

	public static UserResponseDto fromEntity(UserEntity user) {
		return new UserResponseDto();
	}


	static List<UserResponseDto> fromEntityList(List<UserEntity> userList) {
		List<UserResponseDto> result = new ArrayList<>();
		for (UserEntity user : userList) {
			UserResponseDto userResponseDto = fromEntity(user);
			result.add(userResponseDto);
		}
		return result;
	}

}
