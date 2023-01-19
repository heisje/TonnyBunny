package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import lombok.Data;


@Data
public class BoardCommentRequestDto {

	public BoardCommentEntity toEntity() {
		return (BoardCommentEntity) new Object();
	}

}
