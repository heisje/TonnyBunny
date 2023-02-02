package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.Data;


@Data
public class JTonnyRequestDto {

	public JTonnyEntity toEntity() {
		return (JTonnyEntity) new Object();
	}

}
