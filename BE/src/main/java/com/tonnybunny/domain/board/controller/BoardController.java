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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
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
	public ResponseEntity<ResultDto<HashMap<String, Object>>> getBoardList(Pageable pageable) {
		Page<BoardEntity> boardList = boardService.getBoardList(pageable);
		Pageable boardPageable = boardList.getPageable();
		int totalPages = boardList.getTotalPages();
		long totalElements = boardList.getTotalElements();
		List<BoardResponseDto> boardResponseDtoList = BoardResponseDto.fromEntityList(boardList.getContent());

		HashMap<String, Object> boardResponseDtoListWithPagiNation = new HashMap<>();
		boardResponseDtoListWithPagiNation.put("pageable", boardPageable);
		boardResponseDtoListWithPagiNation.put("totalPages", totalPages);
		boardResponseDtoListWithPagiNation.put("totalElements", totalElements);
		boardResponseDtoListWithPagiNation.put("content", boardResponseDtoList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardResponseDtoListWithPagiNation));
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
	public ResponseEntity<ResultDto<Long>> createBoard(BoardRequestDto boardRequestDto, MultipartHttpServletRequest request) {
		Long boardSeq = boardService.createBoard(boardRequestDto, request);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(boardSeq));
	}


	@PutMapping("/{boardSeq}")
	@ApiOperation(value = "게시글을 수정합니다.", notes = "")
	public ResponseEntity<ResultDto<Long>> modifyBoard(@RequestBody BoardRequestDto boardRequestDto,
		@PathVariable Long boardSeq) {

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
	public ResponseEntity<ResultDto<Boolean>> createBoardComment(
		@PathVariable("boardSeq") Long boardSeq,
		@RequestBody BoardCommentRequestDto boardCommentRequestDto) {
		boardService.createBoardComment(boardSeq, boardCommentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@PutMapping("/{boardSeq}/comment/{commentSeq}")
	@ApiOperation(value = "게시글의 댓글을 수정합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> modifyBoardComment(
		@PathVariable("boardSeq") Long boardSeq, @PathVariable("commentSeq") Long commentSeq,
		@RequestBody BoardCommentRequestDto boardCommentRequestDto) {
		boardService.modifyBoardComment(commentSeq, boardCommentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@DeleteMapping("/{boardSeq}/comment/{commentSeq}")
	@ApiOperation(value = "게시글의 댓글을 삭제합니다.", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteBoardComment(
		@PathVariable("boardSeq") Long boardSeq, @PathVariable("commentSeq") Long commentSeq) {
		boardService.deleteBoardComment(commentSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
