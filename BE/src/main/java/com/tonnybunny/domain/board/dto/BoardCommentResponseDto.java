package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BoardCommentResponseDto {

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
