package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class FollowResponseDto {

	public static FollowResponseDto fromEntity(FollowEntity follow) {
		return new FollowResponseDto();
	}


	public static List<FollowResponseDto> fromEntityList(List<FollowEntity> followList) {
		List<FollowResponseDto> result = new ArrayList<>();

		for (FollowEntity follow : followList) {
			FollowResponseDto followResponseDto = fromEntity(follow);
			result.add(followResponseDto);
		}

		return result;

	}

}
