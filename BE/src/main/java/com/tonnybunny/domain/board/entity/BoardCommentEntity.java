package com.tonnybunny.domain.board.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // @Builder와 같이 씀
@Builder
@Table(name = "board_comment_table")
public class BoardCommentEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_comment_seq")
	private Long seq;

	private String content;

	@ManyToOne(targetEntity = BoardEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "board_seq")
	private BoardEntity board;

	/* user 가 본인이 작성한 댓글 리스트를 들고 있을 필요가 없어서 단방향으로 설정 */
	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;

}
