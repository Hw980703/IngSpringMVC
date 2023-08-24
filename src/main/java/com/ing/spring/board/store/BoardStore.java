package com.ing.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.board.domain.Board;
import com.ing.spring.board.domain.PageInfo;


public interface BoardStore {

	int insertBoard(SqlSession session , Board board);

	int selectListCount(SqlSession session);

	List<Board> selectBoardList(SqlSession session, PageInfo pInfo);

	Board selectBoardByNo(SqlSession session, Integer boardNo);
	
}
