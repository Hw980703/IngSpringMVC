package com.ing.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.member.domain.Member;

public interface MemberStore {

	int insertMember(SqlSession session, Member member);

	int checkMemberLogin(SqlSession session, Member member);

}
