package com.ing.spring.board.service;

import java.util.List;

import com.ing.spring.board.domain.Reply;

public interface ReplyService {

	
	/**
	 * 게시글 등록 서비스
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);

	List<Reply> selectReplyList(int refBoardNo);

	int updateReply(Reply reply);

}
