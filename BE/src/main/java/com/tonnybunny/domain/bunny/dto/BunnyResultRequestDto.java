package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyResultEntity;
import lombok.Data;


@Data
public class BunnyResultRequestDto {

	public BunnyResultEntity toEntity() {
		return (BunnyResultEntity) new Object();
	}

}
