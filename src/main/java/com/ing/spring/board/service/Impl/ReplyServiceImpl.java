package com.ing.spring.board.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.spring.board.domain.Reply;
import com.ing.spring.board.service.ReplyService;
import com.ing.spring.board.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyStore rStore;
	
	@Autowired
	private SqlSession session;
	
	
	@Override
	public int insertReply(Reply reply) {
		
		int result = rStore.insertReply(session,reply);
		
		return result;
	}


	/**
	 * 댓글 전체 조회
	 */
	@Override
	public List<Reply> selectReplyList(int refBoardNo) {
		List<Reply> rList = rStore.selectReplyList(session,refBoardNo);
		return rList;
	}


	@Override
	public int updateReply(Reply reply) {
		
		int result = rStore.updateReply(session,reply);
		
		return result;
	}

}
