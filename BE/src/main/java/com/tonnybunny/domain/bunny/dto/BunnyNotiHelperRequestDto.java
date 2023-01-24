package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import lombok.Data;


@Data
public class BunnyNotiHelperRequestDto {

	public BunnyNotiHelperEntity toEntity() {
		return (BunnyNotiHelperEntity) new Object();
	}

}
