package com.ing.spring.member.domain;

import java.sql.Timestamp;

public class Member {
	
		private String memberId;
		private String memberPw;
		private String memberName;
		private int memberAge;
		private String memberGender;
		private String memberEmail;
		private String memberPhone;
		private String memberAddress;
		private String memberHobby;
		private Timestamp memberDate;
		private Timestamp updateDate;
		private String memberYn;
		
		
		
		
		public Member(String memberId, String memberName, int memberAge, String memberGender, String memberEmail,
				String memberPhone, String memberAddress, String memberHobby, Timestamp memberDate) {
			super();
			this.memberId = memberId;
			this.memberName = memberName;
			this.memberAge = memberAge;
			this.memberGender = memberGender;
			this.memberEmail = memberEmail;
			this.memberPhone = memberPhone;
			this.memberAddress = memberAddress;
			this.memberHobby = memberHobby;
			this.memberDate = memberDate;
		}
		public Member(String memberId, String memberPw, String memberName, int memberAge, String memberGender,
				String memberEmail, String memberPhone, String memberAddress, String memberHobby) {
			super();
			this.memberId = memberId;
			this.memberPw = memberPw;
			this.memberName = memberName;
			this.memberAge = memberAge;
			this.memberGender = memberGender;
			this.memberEmail = memberEmail;
			this.memberPhone = memberPhone;
			this.memberAddress = memberAddress;
			this.memberHobby = memberHobby;
		}
		public Member() {
			super();
		}
		public Member(String memberId, String memberPw, String memberNAME, int memberAGE, String memberGender,
				String memberEmail, String memberPhone, String memberAddress, String memberHobby, Timestamp memberDate,
				Timestamp updateDate, String memberYn) {
			super();
			this.memberId = memberId;
			this.memberPw = memberPw;
			this.memberName = memberNAME;
			this.memberAge = memberAGE;
			this.memberGender = memberGender;
			this.memberEmail = memberEmail;
			this.memberPhone = memberPhone;
			this.memberAddress = memberAddress;
			this.memberHobby = memberHobby;
			this.memberDate = memberDate;
			this.updateDate = updateDate;
			this.memberYn = memberYn;
		}
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getMemberPw() {
			return memberPw;
		}
		public void setMemberPw(String memberPw) {
			this.memberPw = memberPw;
		}
		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberNAME) {
			this.memberName = memberNAME;
		}
		public int getMemberAge() {
			return memberAge;
		}
		public void setMemberAge(int memberAGE) {
			this.memberAge = memberAGE;
		}
		public String getMemberGender() {
			return memberGender;
		}
		public void setMemberGender(String memberGender) {
			this.memberGender = memberGender;
		}
		public String getMemberEmail() {
			return memberEmail;
		}
		public void setMemberEmail(String memberEmail) {
			this.memberEmail = memberEmail;
		}
		public String getMemberPhone() {
			return memberPhone;
		}
		public void setMemberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
		}
		public String getMemberAddress() {
			return memberAddress;
		}
		public void setMemberAddress(String memberAddress) {
			this.memberAddress = memberAddress;
		}
		public String getMemberHobby() {
			return memberHobby;
		}
		public void setMemberHobby(String memberHobby) {
			this.memberHobby = memberHobby;
		}
		public Timestamp getMemberDate() {
			return memberDate;
		}
		public void setMemberDate(Timestamp memberDate) {
			this.memberDate = memberDate;
		}
		public Timestamp getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Timestamp updateDate) {
			this.updateDate = updateDate;
		}
		public String getMemberYn() {
			return memberYn;
		}
		public void setMemberYn(String memberYn) {
			this.memberYn = memberYn;
		}
		@Override
		public String toString() {
			return "ȸ�� [���̵� =" + memberId + ", ��й�ȣ =" + memberPw + ", �̸� =" + memberName + ", ���� ="
					+ memberAge + ", ���� =" + memberGender + ", �̸��� =" + memberEmail + ", ��ȭ��ȣ ="
					+ memberPhone + ", �ּ� =" + memberAddress + ", ��� =" + memberHobby + ", ���Գ�¥ ="
					+ memberDate + ", ������¥ =" + updateDate + ", ȸ������ =" + memberYn + "]";
		}
		
		
		
		
		
		

}
