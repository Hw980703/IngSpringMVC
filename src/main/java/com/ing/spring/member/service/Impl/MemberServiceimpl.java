package com.ing.spring.member.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.spring.member.domain.Member;
import com.ing.spring.member.service.MemberSerivce;
import com.ing.spring.member.store.MemberStore;

@Service
public class MemberServiceimpl implements MemberSerivce{
	@Autowired
	private SqlSession session;
	
	@Autowired
	private MemberStore mStore;
	
	
	@Override
	public int insertMember(Member member) {
		int result = mStore.insertMember(session,member);
		
		return result;
	}


	@Override
	public Member checkMemberLogin(Member member) {
		
		Member mOne = mStore.checkMemberLogin(session,member);
		
		return mOne;
	}


	@Override
	public Member checkMEmberById(Member member) {
		
		Member mOne = mStore.checkMEmberById(session,member);
		
		return mOne;
	}


	@Override
	public int modifyMember(Member member) {
		
		int result = mStore.modifyMember(session , member);
		
		return result;
	}


	@Override
	public int deleteMeber(Member member) {
		
		int result = mStore.deleteMember(session,member);
		
		return result;
	}

}
