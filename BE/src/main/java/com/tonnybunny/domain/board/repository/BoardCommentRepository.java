package com.tonnybunny.domain.board.repository;


import com.tonnybunny.domain.board.entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {

	List<BoardCommentEntity> findByBoard(Long boardSeq);

}
