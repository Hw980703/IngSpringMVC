package com.ing.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.domain.PageInfo;

public interface NoticeStore {

	int insertNotice(SqlSession session, Notice notice);

	List<Notice> selectNoticeList(SqlSession session,PageInfo pInfo);

	/**
	 * 
	 * 공지사항 개수 조회
	 * @param session
	 * @return
	 */
	
	int getListCount(SqlSession session);

	List<Notice> searchNoticeByTitle(SqlSession session, String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeAll(SqlSession session, String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeByWriter(SqlSession session, String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeByContent(SqlSession session, String searchKeyword,PageInfo pInfo);

	
	/**
	 * 공지사항 조건에 따라 키워드로 조회 
	 * @param session
	 * @param searchCondiition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticeByKeyword(SqlSession session,PageInfo pInfo, Map<String,String> paraMap);

	int selectListCount(SqlSession session, Map<String, String> paramMap);

		/**
		 * 공지사항 번호로 조회
		 * @param session
		 * @param noticeNo
		 * @return
		 */
	Notice selectNoticeByNo(SqlSession session, Integer noticeNo);

		int updateNotice(SqlSession session, Notice notice);

//	List<Notice> selectNoticeList(SqlSession session, int curruntPage);

}
