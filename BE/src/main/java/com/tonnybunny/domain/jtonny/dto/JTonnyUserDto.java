package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.user.dto.UserCodeEnum;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;


@Data
@NoArgsConstructor
public class JTonnyUserDto {

	private Long seq;
	private String userCode;
	private String nickName;
	private String profileImagePath = File.separator + "noProfile.png";
	private JTonnyHelperInfoDto helperInfo;


	public JTonnyUserDto(UserEntity user) {
		this.seq = user.getSeq();
		this.userCode = user.getUserCode();
		this.nickName = user.getNickName();
		this.profileImagePath = user.getProfileImagePath();
		if (this.userCode.equals(UserCodeEnum.클라이언트.getUserCode())) {
			this.helperInfo = new JTonnyHelperInfoDto();
		} else {
			this.helperInfo = JTonnyHelperInfoDto.fromEntity(user.getHelperInfo());
		}

	}

}
