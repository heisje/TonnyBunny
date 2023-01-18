package com.tonnybunny.domain.chat.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatLogEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_log_seq")
	private Long seq;

	private Long charRoomSeq;

	private Long userSeq;

	private String date;

	private String dateTypeCode;

}