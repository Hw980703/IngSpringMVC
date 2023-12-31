package com.ing.spring.board.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.spring.board.domain.Board;
import com.ing.spring.board.domain.PageInfo;
import com.ing.spring.board.service.BoardService;
import com.ing.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	SqlSession session;
	
	@Autowired
	BoardStore bStore;
	
	
	@Override
	public int insertBoard(Board board) {
		
		int result = bStore.insertBoard(session , board);
		
		return result;
	}


	@Override
	public int getListCount() {
		
		int result = bStore.selectListCount(session);
		
		return result;
	}


		/**
		 * 게시글 전체 조회 서비스
		 */
	@Override
	public List<Board> selectBoardList(PageInfo pInfo) {
		
		List<Board> bList = bStore.selectBoardList(session,pInfo);
		
		
		return bList;
	}


		@Override
		public Board selectBoardByNo(Integer boardNo) {
			
			Board bOne = bStore.selectBoardByNo(session,boardNo);
			
			return bOne;
		}

}
