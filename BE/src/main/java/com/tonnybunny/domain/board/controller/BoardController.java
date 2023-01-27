package com.tonnybunny.domain.board.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.board.dto.BoardCommentRequestDto;
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
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = "게시판 관련 API")
public class BoardController {

	private final BoardService boardService;


	/* 게시글 */


	@GetMapping
	@ApiOperation(value = "게시글 리스트를 조회합니다.", notes = "")
	public ResponseEntity<ResultDto<List<BoardResponseDto>>> getBoardList() {
		List<BoardEntity> boardList = boardService.getBoardList();
		List<BoardResponseDto> boardResponseDtoList = BoardResponseDto.fromEntityList(boardList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardResponseDtoList));
	}


	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "게시글을 열람합니다.", notes = "")
	public ResponseEntity<ResultDto<BoardResponseDto>> getBoard(@PathVariable Long boardSeq) {
		BoardEntity board = boardService.getBoard(boardSeq);
		BoardResponseDto boardResponseDto = BoardResponseDto.fromEntity(board);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardResponseDto));
	}


	@PostMapping
	@ApiOperation(value = "게시글을 작성합니다.", notes = "")
	public ResponseEntity<ResultDto<Long>> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
		Long boardSeq = boardService.createBoard(boardRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardSeq));
	}


	@PutMapping("/{boardSeq}")
	@ApiOperation(value = "게시글을 수정합니다.", notes = "")
	public ResponseEntity<ResultDto<Long>> modifyBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable Long boardSeq) {

		Long updatedBoardSeq = boardService.modifyBoard(boardSeq, boardRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedBoardSeq));
	}


	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "게시글을 삭제합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteBoard(@PathVariable Long boardSeq) {
		boardService.deleteBoard(boardSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/* 게시글 댓글 */


	@PostMapping("/{boardSeq}/comment")
	@ApiOperation(value = "게시글의 댓글을 작성합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> createBoardComment(@PathVariable("boardSeq") Long boardSeq, @RequestBody BoardCommentRequestDto boardCommentRequestDto) {
		boardService.createBoardComment(boardSeq, boardCommentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@PutMapping("/{boardSeq}/comment/{commentSeq}")
	@ApiOperation(value = "게시글의 댓글을 수정합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> modifyBoardComment(@PathVariable("boardSeq") Long boardSeq, @PathVariable("commentSeq") Long commentSeq,
		@RequestBody BoardCommentRequestDto boardCommentRequestDto) {
		boardService.modifyBoardComment(commentSeq, boardCommentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@DeleteMapping("/{boardSeq}/comment/{commentSeq}")
	@ApiOperation(value = "게시글의 댓글을 삭제합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteBoardComment(@PathVariable("boardSeq") Long boardSeq, @PathVariable("commentSeq") Long commentSeq) {
		boardService.deleteBoardComment(commentSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
