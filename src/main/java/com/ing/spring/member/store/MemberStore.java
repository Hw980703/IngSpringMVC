package com.ing.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.ing.spring.member.domain.Member;

public interface MemberStore {

	int insertMember(SqlSession session, Member member);

	Member checkMemberLogin(SqlSession session, Member member);

	Member checkMEmberById(SqlSession session, Member member);

	int modifyMember(SqlSession session, Member member);

	int deleteMember(SqlSession session, Member member);

}
