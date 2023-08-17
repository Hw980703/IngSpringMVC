package com.ing.spring.notice.service.imple;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.service.NoticeService;
import com.ing.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeStore nStore;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertNotice(Notice notice) {
		
		int result = nStore.insertNotice(session,notice);
		
		return result;
	}

	@Override
	public List<Notice> selectNoticeList() {
		
		List<Notice> nList = nStore.selectNoticeList(session);
		
		return nList;
	}
	
	

}
