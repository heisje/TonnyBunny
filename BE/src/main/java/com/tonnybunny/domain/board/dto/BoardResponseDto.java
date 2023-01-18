package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BoardResponseDto {

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
