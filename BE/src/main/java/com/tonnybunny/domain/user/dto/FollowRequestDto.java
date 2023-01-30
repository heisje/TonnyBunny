package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Data;


@Data
public class FollowRequestDto {

	public FollowEntity toEntity() {
		return (FollowEntity) new Object();
	}

}
