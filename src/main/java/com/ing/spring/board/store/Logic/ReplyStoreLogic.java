package com.ing.spring.board.store.Logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ing.spring.board.domain.Reply;
import com.ing.spring.board.service.ReplyService;
import com.ing.spring.board.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore {

	@Override
	public int insertReply(SqlSession session, Reply reply) {
		
		int result = session.insert("ReplyMapper.insertReply",reply);
		
		return result;
	}

	/**
	 * 댓글 전체 조회
	 */
	@Override
	public List<Reply> selectReplyList(SqlSession session, int refBoardNo) {
		
		List<Reply> rList = session.selectList("ReplyMapper.selectReplyList",refBoardNo);
		
		return rList;
	}

	@Override
	public int updateReply(SqlSession session, Reply reply) {
		
		int result = session.update("ReplyMapper.updateReply",reply);
		
		return result;
	}

}
