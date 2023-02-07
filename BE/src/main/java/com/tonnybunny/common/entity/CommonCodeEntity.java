package com.tonnybunny.common.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "common_code_table")
public class CommonCodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_seq")
	private Long codeSeq;

	@ManyToOne
	@JoinColumn(name = "group_code")
	private CommonGroupCodeEntity groupCode;

	private String code;
	private String codeName;

}
