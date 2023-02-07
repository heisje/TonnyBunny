package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoImageEntity;
import lombok.Data;


@Data
public class HelperInfoImageRequestDto {
	
	private String imagePath;


	public HelperInfoImageEntity toEntity() {
		return (HelperInfoImageEntity) new Object();
	}

}
