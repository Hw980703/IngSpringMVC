package com.ing.spring.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.notice.domain.Notice;

public interface NoticeStore {

	int insertNotice(SqlSession session, Notice notice);

	List<Notice> selectNoticeList(SqlSession session);

}
