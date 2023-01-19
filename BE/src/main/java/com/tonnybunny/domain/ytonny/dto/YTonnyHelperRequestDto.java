package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import lombok.Data;


@Data
public class YTonnyHelperRequestDto {

	public YTonnyNotiHelperEntity toEntity() {
		return (YTonnyNotiHelperEntity) new Object();
	}

}
