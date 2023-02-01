package com.tonnybunny.domain.board.service;


import com.tonnybunny.domain.board.dto.BoardCommentRequestDto;
import com.tonnybunny.domain.board.dto.BoardRequestDto;
import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import com.tonnybunny.domain.board.entity.BoardEntity;
import com.tonnybunny.domain.board.repository.BoardRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	private final UserRepository userRepository;


	/**
	 * repository 에서 findBoardList() 를 수행한다.
	 *
	 * @return List<BoardEntity>
	 */
	public List<BoardEntity> getBoardList() {

		List<BoardEntity> boardList = boardRepository.findAll();
		//FIXME : 삭제된 보드 제외
		return boardList;
	}


	/**
	 * repository 에서 findBoardBySeq(boardSeq) 를 수행한다.
	 *
	 * @param boardSeq
	 * @return BoardEntity
	 */
	public BoardEntity getBoard(Long boardSeq) throws Exception {
		BoardEntity board = boardRepository.findById(boardSeq)
		                                   .orElseThrow(() -> new Exception("존재하지 않는 게시글입니다."));
		return board;
	}


	/**
	 * repository 에서 insertBoard(board) 를 수행한다.
	 * JPA 가 board 에 키 값을 넣어줌.
	 *
	 * @param boardRequestDto
	 * @return boardEntity
	 */
	public BoardEntity createBoard(BoardRequestDto boardRequestDto) throws Exception {
		//		UserEntity fromUser = userRepository.findById(boardRequestDto.getUserSeq())
		//		                                    .orElseThrow(() -> new Exception("존재하지 않는 사용자입니다."));
		BoardEntity board =
			boardRepository.save(
				BoardEntity.builder()
				           //				           .user(fromUser)
				           .title(boardRequestDto.getTitle())
				           .content(boardRequestDto.getContent())
				           //FIXME : 유저, 사진 추가
				           .build());

		return board;
	}


	/**
	 * oldBoard = repository.findBoardBySeq(boardSeq) 를 수행한다.
	 * _old 에 _new 를 덮어씌운다.
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


	/**
	 * repository 에서 insertBoardComment(boardComment) 를 통해 댓글을 등록한다.
	 * repository 에서 findBoardBySeq(boardSeq) 를 통해 BoardEntity 를 가져온다.
	 * BoardEntity 내부의 addComment(boardComment) 를 통해 댓글 정보를 추가한다.
	 *
	 * @param boardSeq               : 댓글이 작성되는 게시글의 pk
	 * @param boardCommentRequestDto : 작성되는 댓글의 정보를 담고 있는 Dto
	 */
	public void createBoardComment(Long boardSeq, BoardCommentRequestDto boardCommentRequestDto) {
		BoardCommentEntity boardComment = boardCommentRequestDto.toEntity();
	}


	/**
	 * repository 에서 findBoardCommentBySeq(commentSeq) 를 통해 BoardCommentEntity 를 가져온다.
	 * 내용을 수정한다.
	 *
	 * @param commentSeq             : 수정할 댓글의 pk
	 * @param boardCommentRequestDto : 수정할 댓글의 정보를 담고 있는 Dto
	 */
	public void modifyBoardComment(Long commentSeq, BoardCommentRequestDto boardCommentRequestDto) {
		BoardCommentEntity boardComment = boardCommentRequestDto.toEntity();
	}


	/**
	 * repository 에서 removeBoardComment(commentSeq) 를 통해 댓글을 삭제한다.
	 * Entity 내부 로직 필요한가?
	 *
	 * @param commentSeq : 삭제할 댓글의 pk
	 */
	public void deleteBoardComment(Long commentSeq) {

	}

}
