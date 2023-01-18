package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserRequestDto {

	public UserEntity toEntity() {
		return (UserEntity) new Object();
	}

}
