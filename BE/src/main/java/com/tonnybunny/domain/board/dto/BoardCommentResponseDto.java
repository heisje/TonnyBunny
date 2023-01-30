package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                      : 댓글 seq
 *
 * boardSeq                 : 게시글 seq
 * user                     : 작성자
 * content                  : 내용
 * createdAt                : 생성시간
 * updatedAt                : 수정시간
 */
@Data
public class BoardCommentResponseDto {

	private Long seq;

	private Long boardSeq;
	private UserResponseDto user;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public static BoardCommentResponseDto fromEntity(BoardCommentEntity boardComment) {
		return new BoardCommentResponseDto();
	}


	public static List<BoardCommentResponseDto> fromEntityList(
		List<BoardCommentEntity> boardCommentList) {
		List<BoardCommentResponseDto> result = new ArrayList<>();

		for (BoardCommentEntity boardComment : boardCommentList) {
			BoardCommentResponseDto boardCommentResponseDto = fromEntity(boardComment);
			result.add(boardCommentResponseDto);
		}

		return result;

	}

}
