package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class JTonnyUserDto {

	private Long seq;
	private String nickName;


	public JTonnyUserDto(UserEntity user) {
		this.seq = user.getSeq();
		this.nickName = user.getNickName();
	}

}
