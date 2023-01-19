package com.tonnybunny.domain.board.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.board.dto.BoardRequestDto;
import com.tonnybunny.domain.board.dto.BoardResponseDto;
import com.tonnybunny.domain.board.entity.BoardEntity;
import com.tonnybunny.domain.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "게시판 관련 API")
public class BoardController {

	private final BoardService boardService;


	/* 게시글 */
	@GetMapping("/board")
	@ApiOperation(value = "게시글 리스트를 조회합니다.", notes = "")
	public ResponseEntity<?> getBoardList() {

		List<BoardEntity> boardList = boardService.getBoardList();
		List<BoardResponseDto> boardResponseDtoList = BoardResponseDto.fromEntityList(boardList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardResponseDtoList));
	}


	@GetMapping("/board/{boardSeq}")
	@ApiOperation(value = "게시글을 열람합니다.", notes = "")
	public ResponseEntity<?> getBoard(@PathVariable Long boardSeq) {

		BoardEntity board = boardService.getBoard(boardSeq);
		BoardResponseDto boardResponseDto = BoardResponseDto.fromEntity(board);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardResponseDto));
	}


	@PostMapping("/board")
	@ApiOperation(value = "게시글을 작성합니다.", notes = "")
	public ResponseEntity<?> createBoard(@RequestBody BoardRequestDto boardRequestDto) {

		Long boardSeq = boardService.createBoard(boardRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardSeq));
	}


	@PutMapping("/board/{boardSeq}")
	@ApiOperation(value = "게시글을 수정합니다.", notes = "")
	public ResponseEntity<?> modifyBoard(@RequestBody BoardRequestDto boardRequestDto,
		@PathVariable Long boardSeq) {

		Long updatedBoardSeq = boardService.modifyBoard(boardSeq, boardRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedBoardSeq));
	}


	@DeleteMapping("/board/{boardSeq}")
	@ApiOperation(value = "게시글을 삭제합니다.", notes = "")
	public ResponseEntity<?> deleteBoard(@PathVariable Long boardSeq) {

		boardService.deleteBoard(boardSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

	/* 게시글 댓글 */

}
