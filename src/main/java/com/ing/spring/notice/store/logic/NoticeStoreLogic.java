package com.ing.spring.notice.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.domain.PageInfo;
import com.ing.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		
		int result = session.insert("NoticeMapper.insertNotice",notice);
		
		return result;
	}

	
	//리스트 출력
	@Override
	public List<Notice> selectNoticeList(SqlSession session,PageInfo pInfo) {

		//limit 은 행의 개수 제어하는거임 10개씩 가져오고싶으면 10
		int limit = pInfo.getRecordCountPerPage();
		
		// offset 은 변하는 default 값이라고 보면 됨
		// 시작하는 값을 세팅해줌 (1,2,3,4,5)
		//1페이지 볼때는 1~10을 봐야함
		//2페이지 볼때는 11~20 봐야함
		// 계속해서 값이 바껴야함
		// 그렇기때문에 curruntPage를 매개변수로 넘겨주고 -1 한 값에 limit을 곱해준다
		int offset = (pInfo.getCurruntPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		//rowBounds 는 3번째 자리에 넣어야함, 가운데에는 아무것도 없을때 Null 넣어줌
		// 넘겨주는 값이 있으면 null 말고 매개변수 넣어주는거임, 여기는 매개변수가 없음
		List<Notice> nList = session.selectList("NoticeMapper.noticeAllSelect",null,rowBounds);
		return nList;
	}
	
	//페이징 처리
//	public void generatePageNavi() {
//		
//		
//		
//	}


//	@Override
//	public List<Notice> selectNoticeList(SqlSession session) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public int getListCount(SqlSession session) {
		
		int result = session.selectOne("NoticeMapper.selectNoticeCount");
		
		return result;
	}


	@Override
	public List<Notice> searchNoticeByTitle(SqlSession session, String searchKeyword,PageInfo pInfo) {
		
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurruntPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeByTitle",searchKeyword,rowBounds);
		
		return nList;
	}


	@Override
	public List<Notice> searchNoticeAll(SqlSession session, String searchKeyword,PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurruntPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeAll",searchKeyword,rowBounds);
				
		
		return nList;
	}


	@Override
	public List<Notice> searchNoticeByWriter(SqlSession session, String searchKeyword,PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurruntPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeByWriter",searchKeyword,rowBounds);
		return nList;
	}


	@Override
	public List<Notice> searchNoticeByContent(SqlSession session, String searchKeyword,PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurruntPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeByContent",searchKeyword,rowBounds);
		
		return nList;
	}


	@Override
	public List<Notice> searchNoticeByKeyword(SqlSession session,PageInfo pInfo, Map<String,String> paraMap) {
	
		
		//2개의 값을 포함해서 총 세가지 쿼리를 주지 못하기 때문에 해결할 수 있는 방법
		// 1. VO 클래스를 만들어서 넘겨주기
		// 2. HashMap 사용하기
		
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurruntPage()-1)*limit;
		
		RowBounds rowbounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeByKeyword",paraMap,rowbounds);
		
		return nList;
	}


	@Override
	public int selectListCount(SqlSession session, Map<String, String> paramMap) {
	
			int result = session.selectOne("NoticeMapper.selectListByKewordCount",paramMap);
		
		return result;
	}

}
