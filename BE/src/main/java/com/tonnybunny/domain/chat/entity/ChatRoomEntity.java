package com.tonnybunny.domain.chat.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatRoomEntity extends CommonEntity {

	@Id
	@Column(name = "chat_room_seq")
	private String seq;    // FIXME : String으로 변경함

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "user_less_seq")
	//	private UserEntity userLess;
	private Long userLessSeq;

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "user_larger_seq")
	//	private UserEntity userLarger;
	private Long userLargerSeq;

}
