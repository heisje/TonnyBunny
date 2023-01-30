package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import lombok.Data;


/**
 * boardSeq             : 게시글 seq
 * userSeq              : 작성자 seq
 * content              : 내용
 */
@Data
public class BoardCommentRequestDto {

	/* 댓글 작성 */
	private Long boardSeq;
	private Long userSeq;
	private String content;


	public BoardCommentEntity toEntity() {
		return (BoardCommentEntity) new Object();
	}

}
