package com.tonnybunny.domain.chat.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_room_seq")
	private String seq;    // FIXME : String으로 변경함

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;

}
