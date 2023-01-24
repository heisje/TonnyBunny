package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyNotiEntity;
import lombok.Data;


@Data
public class JTonnyNotiRequestDto {

	public JTonnyNotiEntity toEntity() {
		return (JTonnyNotiEntity) new Object();
	}
	
}
