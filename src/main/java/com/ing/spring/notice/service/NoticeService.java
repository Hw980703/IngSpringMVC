package com.ing.spring.notice.service;

import java.util.List;

import com.ing.spring.notice.domain.Notice;

public interface NoticeService {

	int insertNotice(Notice notice);

	List<Notice> selectNoticeList();

}
