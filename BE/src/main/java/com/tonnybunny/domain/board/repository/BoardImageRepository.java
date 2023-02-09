package com.tonnybunny.domain.board.repository;


import com.tonnybunny.domain.board.entity.BoardImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {

}