package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 반환하는 유저의 키 값
 * userCode             : 유저공통코드 (관리자, 일반유저, 헬퍼유저)
 * email                : 유저의 이메일
 * nickName             : 유저의 닉네임
 * password             : 반환하는 경우가있는가? 없으면 지울 것
 * phoneNumber          : 반환하는 경우가 있는가? 없으면 지울 것
 * point                : 유저가 현재 보유중인 포인트
 * reportCount          : 신고 누적 카운트
 * profileImagePath     : 유저프로필사진 경로
 * helperInfoResponseDto: 헬퍼 정보
 */
@Data
@Builder
public class UserResponseDto {

	private Long seq;
	private String userCode;

	private String email;
	private String nickName;
	//	private String password;
	private String phoneNumber;

	private Long point;
	//	private Integer reportCount;
	private String profileImagePath;

	private HelperInfoResponseDto helperInfo;


	/**
	 * block, follow List 등을 List<Dto> 로 가지고 있을지 List<Long> 으로 가지고 있을지? HelperInfoResponseDto 의 분리?
	 */

	public static UserResponseDto fromEntity(UserEntity user) {
		UserResponseDto userResponseDto = UserResponseDto.builder()
		                                                 .seq(user.getSeq())
		                                                 .email(user.getEmail())
		                                                 .nickName(user.getNickName())
		                                                 .profileImagePath(user.getProfileImagePath())
		                                                 .phoneNumber(user.getPhoneNumber())
		                                                 .point(user.getPoint())
		                                                 .userCode(user.getUserCode())
		                                                 //		                                                 .helperInfo(HelperInfoResponseDto.fromEntity(user.getHelperInfo()))
		                                                 .build();
		return userResponseDto;
	}


	public static UserResponseDto createUserWithHelper(UserEntity user) {
		UserResponseDto userResponseDto = UserResponseDto.builder()
		                                                 .seq(user.getSeq())
		                                                 .email(user.getEmail())
		                                                 .nickName(user.getNickName())
		                                                 .profileImagePath(user.getProfileImagePath())
		                                                 .phoneNumber(user.getPhoneNumber())
		                                                 .point(user.getPoint())
		                                                 .userCode(user.getUserCode())
		                                                 .helperInfo(HelperInfoResponseDto.fromEntity(user.getHelperInfo()))
		                                                 .build();
		return userResponseDto;
	}


	public static List<UserResponseDto> fromEntityList(List<UserEntity> userList) {
		List<UserResponseDto> result = new ArrayList<>();
		for (UserEntity user : userList) {
			UserResponseDto userResponseDto = fromEntity(user);
			result.add(userResponseDto);
		}
		return result;
	}

}
