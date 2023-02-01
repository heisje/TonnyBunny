package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 반환하는 유저의 키 값
 * email                : 유저의 이메일
 * nickName             : 유저의 닉네임
 * profileImagePath     : 유저프로필사진 경로
 */
@Data
public class FollowResponseDto {

	private Long seq;
	private String nickName;
	private String profileImagePath;

	private UserResponseDto userResponseDto;
	/**
	 * 헬퍼 info 관련 정보
	 */
	private Float avgScore;
	private Integer reviewCount;
	private Integer unitPrice;
	private String oneLineIntroduction;


	@Builder
	private FollowResponseDto(Long seq, String nickName, String profileImagePath, Float avgScore, Integer reviewCount, Integer unitPrice, String oneLineIntroduction) {
		this.seq = seq;
		this.nickName = nickName;
		this.profileImagePath = profileImagePath;
		this.avgScore = avgScore;
		this.reviewCount = reviewCount;
		this.unitPrice = unitPrice;
		this.oneLineIntroduction = oneLineIntroduction;

	}


	public static FollowResponseDto fromEntity(FollowEntity follow) {
		ModelMapper modelMapper = new ModelMapper();
		FollowResponseDto followResponseDto = modelMapper.map(follow, FollowResponseDto.class);
		return followResponseDto;
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
