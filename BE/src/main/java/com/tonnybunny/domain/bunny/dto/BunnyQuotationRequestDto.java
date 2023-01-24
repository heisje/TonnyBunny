package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import lombok.Data;


@Data
public class BunnyQuotationRequestDto {

	public BunnyQuotationEntity toEntity() {
		return (BunnyQuotationEntity) new Object();
	}

}
