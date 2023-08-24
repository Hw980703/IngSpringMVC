package com.ing.spring.board.store.Logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ing.spring.board.domain.Board;
import com.ing.spring.board.domain.PageInfo;
import com.ing.spring.board.store.BoardStore;

@Repository
public class StoreLogic implements BoardStore {

	@Override
	public int insertBoard(SqlSession session , Board board) {
		
		int result  = session.insert("BoardMapper.insertBoard",board);
		
		return result;
	}

	@Override
	public int selectListCount(SqlSession session) {
		
		int result = session.selectOne("BoardMapper.selectBoardCount");
		
		return result;
	}

	
	/**
	 * 게시글 전체 조회
	 */
	@Override
	public List<Board> selectBoardList(SqlSession session, PageInfo pInfo) {
		
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> bList = session.selectList("BoardMapper.boardAllSelect",null,rowBounds);
		
		return bList;
	}

	@Override
	public Board selectBoardByNo(SqlSession session, Integer boardNo) {
		
		Board bOne = session.selectOne("BoardMapper.selectBoardByNo",boardNo);
		
		return bOne;
	}

	
}
