package com.tonnybunny.domain.board.service;


import com.tonnybunny.domain.board.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BoardService {

	public List<BoardEntity> getBoardList() {

		/* repository 에서 게시글 리스트를 조회하여 반환한다. */

		return new ArrayList<>();
	}

}
