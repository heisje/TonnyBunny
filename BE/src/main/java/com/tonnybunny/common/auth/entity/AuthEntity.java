package com.tonnybunny.common.auth.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@RequiredArgsConstructor
public class AuthEntity extends CommonEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String refreshToken;

	@ManyToOne
	@JoinColumn(name = "user_seq")
	private UserEntity user;


	@Builder
	public AuthEntity(String refreshToken, UserEntity user) {
		this.refreshToken = refreshToken;
		this.user = user;
	}


	public void refreshUpdate(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
