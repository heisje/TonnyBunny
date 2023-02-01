package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Data;


/**
 * seq                  : 요청하는 유저의 키 값
 * follow_seq           : 즐겨찾기 대상 유저의 키 값
 */
@Data
public class FollowRequestDto {

	/**
	 * 전체 검색
	 */
	private Long seq;
	/**
	 * 개별 정보 보기, 팔로우 목록에서 삭제
	 */
	private Long follow_seq;


	public FollowEntity toEntity() {
		return (FollowEntity) new Object();
	}

}
