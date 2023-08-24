package com.ing.spring.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.spring.board.domain.Board;
import com.ing.spring.board.domain.PageInfo;

public interface BoardService {

	/*
	 * 게시글 작성
	 */
	int insertBoard(Board board);

	int getListCount();

	List<Board> selectBoardList(PageInfo pInfo);

	Board selectBoardByNo(Integer boardNo);

}
