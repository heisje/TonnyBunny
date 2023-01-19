package com.tonnybunny.domain.board.service;


import com.tonnybunny.domain.board.dto.BoardRequestDto;
import com.tonnybunny.domain.board.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BoardService {

	/**
	 * repository 에서 findBoardList() 를 수행한다.
	 *
	 * @return List<BoardEntity>
	 */
	public List<BoardEntity> getBoardList() {

		return new ArrayList<>();
	}


	/**
	 * repository 에서 findBoardBySeq(boardSeq) 를 수행한다.
	 *
	 * @param boardSeq
	 * @return BoardEntity
	 */
	public BoardEntity getBoard(Long boardSeq) {

		return (BoardEntity) new Object();
	}


	/**
	 * repository 에서 insertBoard(board) 를 수행한다. JPA 가 board 에 키 값을 넣어줌.
	 *
	 * @param boardRequestDto
	 * @return boardSeq
	 */
	public Long createBoard(BoardRequestDto boardRequestDto) {
		BoardEntity board = boardRequestDto.toEntity();

		return board.getSeq();
	}


	/**
	 * oldBoard = repository.findBoardBySeq(boardSeq) 를 수행한다. _old 에 _new 를 덮어씌운다.
	 *
	 * @param boardSeq
	 * @param boardRequestDto
	 * @return boardSeq
	 */
	public Long modifyBoard(Long boardSeq, BoardRequestDto boardRequestDto) {
		BoardEntity newBoard = boardRequestDto.toEntity();

		// common.update(oldDomain, newDomain);

		return 0L;
	}


	/**
	 * repository.removeBoard(boardSeq) 를 수행한다.
	 *
	 * @param boardSeq
	 */
	public Boolean deleteBoard(Long boardSeq) {

		return true;
	}

}
