package com.tonnybunny.common.auth.dto;


import com.tonnybunny.common.auth.entity.AuthEntity;
import lombok.Data;


@Data
public class AuthRequestDto {

	public AuthEntity toEntity() {
		return (AuthEntity) new Object();
	}

}
