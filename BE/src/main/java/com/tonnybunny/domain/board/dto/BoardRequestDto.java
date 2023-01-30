package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardEntity;
import lombok.Data;

import java.util.List;


/**
 * userSeq              : 작성자 seq
 * title                : 제목
 * content              : 내용
 * BoardImageList       : 게시글 이미지 리스트
 */
@Data
public class BoardRequestDto {

	/* 게시글 작성 */
	private Long userSeq;
	private String title;
	private String content;

	private List<BoardImageRequestDto> BoardImageList;


	public BoardEntity toEntity() {
		return (BoardEntity) new Object();
	}

}
