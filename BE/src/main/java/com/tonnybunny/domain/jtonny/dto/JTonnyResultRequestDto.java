package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyResultEntity;
import lombok.Data;


@Data
public class JTonnyResultRequestDto {

	public JTonnyResultEntity toEntity() {
		return (JTonnyResultEntity) new Object();
	}

}
