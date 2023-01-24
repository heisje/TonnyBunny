package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.Data;


@Data
public class HelperInfoRequestDto {

	public HelperInfoEntity toEntity() {
		return (HelperInfoEntity) new Object();
	}

}
