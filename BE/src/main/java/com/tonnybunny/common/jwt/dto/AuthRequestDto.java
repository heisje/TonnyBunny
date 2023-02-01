package com.tonnybunny.common.jwt.dto;


import com.tonnybunny.common.jwt.entity.AuthEntity;
import lombok.Data;


@Data
public class AuthRequestDto {

	public AuthEntity toEntity() {
		return (AuthEntity) new Object();
	}

}
