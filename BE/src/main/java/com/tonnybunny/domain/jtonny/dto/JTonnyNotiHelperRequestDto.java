package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyNotiHelperEntity;
import lombok.Data;


@Data
public class JTonnyNotiHelperRequestDto {

	public JTonnyNotiHelperEntity toEntity() {
		return (JTonnyNotiHelperEntity) new Object();
	}

}
