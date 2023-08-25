package com.ing.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.board.domain.Reply;

public interface ReplyStore {

	int insertReply(SqlSession session, Reply reply);

	List<Reply> selectReplyList(SqlSession session,int refBoardNo);

	int updateReply(SqlSession session, Reply reply);

}
