package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Data;


/**
 * seq                  : 요청하는 유저의 키 값
 */
@Data
public class FollowRequestDto {

	private Long seq;


	public FollowEntity toEntity() {
		return (FollowEntity) new Object();
	}

}
