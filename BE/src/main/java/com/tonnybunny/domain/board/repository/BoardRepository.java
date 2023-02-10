package com.tonnybunny.domain.board.repository;


import com.tonnybunny.domain.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	Page<BoardEntity> findAllByIsDeleteIsFalseOrderByCreatedAtDesc(Pageable pageable);

	Optional<BoardEntity> findById(Long boardSeq);

}
