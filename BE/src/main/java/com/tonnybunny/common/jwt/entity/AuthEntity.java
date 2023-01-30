package com.tonnybunny.common.jwt.entity;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Getter
@RequiredArgsConstructor
@Table(name = "auth")
@Entity
public class AuthEntity {

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
