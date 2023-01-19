package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import lombok.Data;


@Data
public class YTonnyNotiRequestDto {

	public YTonnyNotiEntity toEntity() {
		return (YTonnyNotiEntity) new Object();
	}

}
