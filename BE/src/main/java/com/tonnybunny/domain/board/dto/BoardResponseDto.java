package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 게시글 seq
 *
 * user                 : 작성자
 * title                : 제목
 * content              : 내용
 * createdAt            : 생성시간
 * updatedAt            : 수정시간
 *
 * BoardCommentList     : 게시글 댓글 리스트
 * BoardImageList       : 게시글 이미지 리스트
 */
@Data
public class BoardResponseDto {

	private Long seq;

	private UserResponseDto user;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private List<BoardCommentResponseDto> BoardCommentList;
	private List<BoardImageResponseDto> BoardImageList;


	public static BoardResponseDto fromEntity(BoardEntity board) {
		return new BoardResponseDto();
	}


	public static List<BoardResponseDto> fromEntityList(List<BoardEntity> boardList) {
		List<BoardResponseDto> result = new ArrayList<>();

		for (BoardEntity board : boardList) {
			BoardResponseDto boardResponseDto = fromEntity(board);
			result.add(boardResponseDto);
		}

		return result;

	}

}
