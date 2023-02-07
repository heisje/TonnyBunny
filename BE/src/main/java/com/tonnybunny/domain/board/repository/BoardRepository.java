package com.tonnybunny.domain.board.repository;


import com.tonnybunny.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	List<BoardEntity> findAllByIsDelete(Boolean isDelete);
	Optional<BoardEntity> findById(Long boardSeq);

}
