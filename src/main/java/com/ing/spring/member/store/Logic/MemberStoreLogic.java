package com.ing.spring.member.store.Logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ing.spring.member.domain.Member;
import com.ing.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{
	
	
	

	@Override
	public int insertMember(SqlSession session, Member member) {
		
		int result = session.insert("MemberMapper.insertMember",member);
		
		return result;
	}

	@Override
	public int checkMemberLogin(SqlSession session, Member member) {
		
		int result = session.selectOne("MemberMapper.checkMemberLogin",member);
		
		return result;
	}

}
