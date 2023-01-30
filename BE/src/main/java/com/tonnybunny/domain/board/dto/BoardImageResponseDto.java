package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 이미지 seq
 *
 * boardSeq             : 게시글 seq
 * imagePath            : 이미지 경로
 */
@Data
public class BoardImageResponseDto {

	private Long seq;
	private Long boardSeq;
	private String imagePath;


	public static BoardImageResponseDto fromEntity(BoardImageEntity boardImage) {
		return new BoardImageResponseDto();
	}


	public static List<BoardImageResponseDto> fromEntityList(List<BoardImageEntity> boardImageList) {
		List<BoardImageResponseDto> result = new ArrayList<>();

		for (BoardImageEntity boardImage : boardImageList) {
			BoardImageResponseDto boardImageResponseDto = fromEntity(boardImage);
			result.add(boardImageResponseDto);
		}

		return result;

	}

}