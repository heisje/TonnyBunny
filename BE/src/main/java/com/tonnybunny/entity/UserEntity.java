package com.tonnybunny.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * User 객체
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long seq;

	private String email;
	private String password;
	private String phoneNumber;
	private String nickname;
	private Long point = 0L;
	private int reported_count;
	private String profile_image_path;
	private String user_code;
	//	private List<>

}
