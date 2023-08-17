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
	public Member checkMemberLogin(SqlSession session, Member member) {
		
		Member mOne = session.selectOne("MemberMapper.checkMemberLogin",member);
		
		return mOne;
	}

	@Override
	public Member checkMEmberById(SqlSession session, Member member) {
		
		Member mOne = session.selectOne("MemberMapper.checkMemberById",member);
		
		return mOne;
	}

	@Override
	public int modifyMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.modifyMember",member);
		
		return result;
		
		
	}

	@Override
	public int deleteMember(SqlSession session, Member member) {
		
		int result = session.update("MemberMapper.deleteMember",member);
		
		return result;
	}

}
