package com.tonnybunny.domain.board.dto;


import lombok.Data;


/**
 * boardSeq            : 게시글 seq
 * imagePath           : 이미지 경로
 */
@Data
public class BoardImageRequestDto {

	private Long boardSeq;
	private String imagePath;


	public BoardImageRequestDto toEntity() {
		return (BoardImageRequestDto) new Object();
	}

}
