package com.tonnybunny.domain.board.service;


import com.tonnybunny.domain.board.dto.BoardCommentRequestDto;
import com.tonnybunny.domain.board.dto.BoardRequestDto;
import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import com.tonnybunny.domain.board.entity.BoardEntity;
import com.tonnybunny.domain.board.entity.BoardImageEntity;
import com.tonnybunny.domain.board.repository.BoardCommentRepository;
import com.tonnybunny.domain.board.repository.BoardImageRepository;
import com.tonnybunny.domain.board.repository.BoardRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tonnybunny.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardCommentRepository boardCommentRepository;
	private final BoardImageRepository boardImageRepository;
	private final UserRepository userRepository;

	private final String uploadFolder = "board" + File.separator + "article";

	@Value("${app.file.path}")
	private String uploadPath;


	/**
	 * repository 에서 findBoardList() 를 수행한다.
	 *
	 * @return List<BoardEntity>
	 */
	public List<BoardEntity> getBoardList() {

		List<BoardEntity> boardList = boardRepository.findAllByIsDelete(false);
		return boardList;
	}


	/**
	 * repository 에서 findBoardBySeq(boardSeq) 를 수행한다.
	 *
	 * @param boardSeq
	 * @return BoardEntity
	 */
	public BoardEntity getBoard(Long boardSeq) {
		BoardEntity board = boardRepository.findById(boardSeq)
		                                   .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		// 삭제 됐을 시 excetion
		if (board.getIsDelete() == false) {
			return board;
		} else {
			throw new CustomException(NOT_FOUND_USER);
		}
	}


	/**
	 * repository 에서 insertBoard(board) 를 수행한다.
	 * JPA 가 board 에 키 값을 넣어줌.
	 *
	 * @param boardRequestDto
	 * @return boardSeq
	 */
	@Transactional
	public Long createBoard(BoardRequestDto boardRequestDto, MultipartHttpServletRequest request) {
		UserEntity user = userRepository.findById(boardRequestDto.getUserSeq()).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);

		// isDelete, ImageList는 Builder.default로 넣어준다.
		BoardEntity board = boardRepository.save(BoardEntity.builder()
		                                                    .user(user)
		                                                    .title(boardRequestDto.getTitle())
		                                                    .content(boardRequestDto.getContent())
		                                                    .build());

		boardRepository.save(board);

		// 이미지리스트를 참조시킬 보드의 시퀀스값
		Long boardSeq = board.getSeq();

		// image save
		try {
			// MultipartHttpServletRequest 로 들어온 이미지파일을 이미지리스트로 변환시킨다.
			List<BoardImageEntity> boardImageList = createBoardImageList(boardSeq, request);
			// 만들어 온 이미지리스트를 보드와 연동시킨다.
			board.updateBoardImageList(boardImageList);
			boardRepository.save(board);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이미지 파일 생성 중 오류");
			throw new CustomException(DATA_BAD_REQUEST);
		}
		// 시퀀스값을 반환한다.
		return board.getSeq();
	}


	@Transactional
	public List<BoardImageEntity> createBoardImageList(Long boardSeq, MultipartHttpServletRequest request) {
		System.out.println("BoardService.createBoardImageList");

		// 참조할 보드 엔티티
		BoardEntity board = boardRepository.findById(boardSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		List<BoardImageEntity> boardImageList = new ArrayList<>();

		try {
			// upload file path setting
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdirs();
			System.out.println("uploadDir = " + uploadDir);

			// form file data 가져오기
			List<MultipartFile> fileList = request.getFiles("file");
			System.out.println("fileList = " + fileList);

			if (!fileList.isEmpty() && fileList != null) {

				for (MultipartFile partFile : fileList) {

					// file name
					String originalFilename = partFile.getOriginalFilename();
					String extension = FilenameUtils.getExtension(originalFilename);
					UUID uuid = UUID.randomUUID();
					String fileName = uuid + "." + extension;
					System.out.println("fileName = " + fileName);

					// file object
					String filePath = uploadFolder + File.separator + fileName; // 경로 설정
					// 파일 생성
					File saveFile = new File(uploadPath + File.separator + filePath);
					System.out.println("saveFile = " + saveFile);
					// 지정한 경로로 파일 이동(try-catch 필요)
					partFile.transferTo(saveFile);

					// 새로운 보드 이미지 엔티티 생성
					BoardImageEntity boardImage = BoardImageEntity.builder()
					                                              .board(board)
					                                              .imagePath(File.separator + filePath)
					                                              .build();
					// 저장
					boardImageRepository.save(boardImage);

					// 리스트에 추가
					boardImageList.add(boardImage);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(DATA_BAD_REQUEST);
		}
		// 리스트 반환
		return boardImageList;

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

		BoardEntity board = boardRepository.findById(boardSeq).orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		// param setting
		String title = boardRequestDto.getTitle();
		String content = boardRequestDto.getContent();

		//수정
		board.update(title, content);

		return boardRepository.save(board).getSeq();
	}


	/**
	 * repository.removeBoard(boardSeq) 를 수행한다.
	 *
	 * @param boardSeq
	 */
	public Boolean deleteBoard(Long boardSeq) {

		BoardEntity board = boardRepository.findById(boardSeq).orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		//삭제 isDelete를 T로 바꾼다
		board.delete(true);
		boardRepository.save(board);

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
		UserEntity fromUser = userRepository.findById(boardCommentRequestDto.getUserSeq()).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
		BoardEntity board = boardRepository.findById(boardSeq).orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		boardCommentRepository.save(
			BoardCommentEntity.builder()
			                  .content(boardCommentRequestDto.getContent())
			                  .board(board)
			                  .user(fromUser)
			                  .build());
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
