package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyApplyEntity;
import lombok.Data;


@Data
public class JTonnyApplyRequestDto {

	public JTonnyApplyEntity toEntity() {
		return (JTonnyApplyEntity) new Object();
	}

}
