package com.tonnybunny.domain.board.dto;


import com.tonnybunny.domain.board.entity.BoardEntity;
import lombok.Data;


@Data
public class BoardRequestDto {

	BoardEntity toEntity() {
		return (BoardEntity) new Object();
	}

}
