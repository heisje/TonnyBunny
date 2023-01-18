package com.tonnybunny.domain.board.controller;


import com.tonnybunny.domain.board.dto.BoardResponseDto;
import com.tonnybunny.domain.board.entity.BoardEntity;
import com.tonnybunny.domain.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "게시판 관련 API")
public class BoardController {

	private final BoardService boardService;


	@GetMapping("/board")
	@ApiOperation(value = "게시글 리스트를 조회합니다.", notes = "")
	public ResponseEntity<?> getBoardList() {
		List<BoardEntity> boardList = boardService.getBoardList();
		List<BoardResponseDto> boardResponseDtoList = BoardResponseDto.fromEntityList(boardList);
		return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtoList);
	}

}
