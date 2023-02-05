package com.tonnybunny.domain.chat.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatLogEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_log_seq")
	private Long seq;

	private String roomSeq;

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "user_seq")
	//	private UserEntity user;
	private Long userSeq;    // FIXME : Log라 FK 안하고 분리함

	private String message;

	private LocalDateTime date;

	private String dataTypeCode;

}
