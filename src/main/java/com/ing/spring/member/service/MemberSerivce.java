package com.ing.spring.member.service;

import com.ing.spring.member.domain.Member;

public interface MemberSerivce {

	int insertMember(Member member);

	Member checkMemberLogin(Member member);

	Member checkMEmberById(Member member);

	int modifyMember(Member member);

	int deleteMeber(Member member);

}
