package com.tonnybunny.domain.board.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_seq")
	private Long seq;

	/**
	 * user 가 본인이 작성한 게시글 리스트를 들고 있을 필요가 없어서 단방향으로 설정
	 * user 의 상태가 비활성화 되었을 때, 작성한 게시글의 상태를 모두 변경하기 위해서 List<BoardEntity> 를 들고있는게 좋나?
	 * 자주 있는 일이 아닌 만큼, 굳이 들고 있지 말고 findBoardBySeq 를 통해 전부 찾아내서 비활성화 시키는 게 바람직해 보임.
	 */
	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;

	private String title;
	private String content;

	/* 게시글의 댓글 리스트 */
	@OneToMany(mappedBy = "board")
	private List<BoardCommentEntity> boardCommentList = new ArrayList<>();

	/* 게시글의 첨부 이미지 리스트 */
	@OneToMany(mappedBy = "board")
	private List<BoardImageEntity> boardImageList = new ArrayList<>();

}
