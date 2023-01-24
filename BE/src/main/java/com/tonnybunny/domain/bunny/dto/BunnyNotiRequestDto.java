package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import lombok.Data;


@Data
public class BunnyNotiRequestDto {

	public BunnyNotiEntity toEntity() {
		return (BunnyNotiEntity) new Object();
	}

}
