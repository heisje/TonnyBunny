package com.tonnybunny.common;


import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class CommonCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_seq")
	private Long seq;

	private String code;

	// 필요한가?
	private String groupCode;

}
