package com.ing.spring.notice.service.imple;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.domain.PageInfo;
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
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		
		List<Notice> nList = nStore.selectNoticeList(session,pInfo);
		
		return nList;
	}

	@Override
	public int getListCount() {
		
		int result = nStore.getListCount(session);
		
		return result;
	}

	@Override
	public List<Notice> searchNoticeByTitle(String searchKeyword,PageInfo pInfo) {
			
		List<Notice> nList = nStore.searchNoticeByTitle(session,searchKeyword,pInfo);
		
		
		return nList;
	}

	@Override
	public List<Notice> searchNoticeAll(String searchKeyword,PageInfo pInfo) {
		
		List<Notice> nList = nStore.searchNoticeAll(session,searchKeyword,pInfo);
		
		return nList;
	}

	@Override
	public List<Notice> searchNoticeByWriter(String searchKeyword,PageInfo pInfo) {
		
		List<Notice> nList = nStore.searchNoticeByWriter(session,searchKeyword,pInfo);
		
		return nList;
	}

	@Override
	public List<Notice> searchNoticeByContent(String searchKeyword,PageInfo pInfo) {
		
		List<Notice> nList = nStore.searchNoticeByContent(session,searchKeyword,pInfo);
		
		return nList;
	}

	@Override
	public List<Notice> searchNoticeByKeyword(PageInfo pInfo,Map<String,String> paraMap) {
		List<Notice> nList = nStore.searchNoticeByKeyword(session,pInfo,paraMap);
		
		return nList;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		
		int result = nStore.selectListCount(session,paramMap);
		
		return result;
	}

	@Override
	public Notice selectNoticeByNo(Integer noticeNo) {
		
		Notice noticeOne = nStore.selectNoticeByNo(session,noticeNo);
		
		return noticeOne;
	}

	@Override
	public int updateNotice(Notice notice) {
		
		int result = nStore.updateNotice(session,notice);
		
		return result;
	}
	
	

}
