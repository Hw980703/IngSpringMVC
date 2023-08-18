package com.ing.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.domain.PageInfo;

public interface NoticeService {

	int insertNotice(Notice notice);

	List<Notice> selectNoticeList(PageInfo pInfo);

	int getListCount();

	List<Notice> searchNoticeByTitle(String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeAll(String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeByWriter(String searchKeyword,PageInfo pInfo);

	List<Notice> searchNoticeByContent(String searchKeyword,PageInfo pInfo);

	
	/**
	 * 공지사항 키워드로 검색
	 * @param searchCondiition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticeByKeyword(PageInfo pInfo, Map<String,String> paraMap);

	int getListCount(Map<String, String> paramMap);

}
