package com.ing.spring.member.service;

import com.ing.spring.member.domain.Member;

public interface MemberSerivce {

	int insertMember(Member member);

	int checkMemberLogin(Member member);

}
