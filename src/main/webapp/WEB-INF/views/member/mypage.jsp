<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<h1>마이페이지</h1>
	<form >
	
	아이디 : <input type="text" name = "memberId" value="${member.memberId }"> <br>
	비밀번호 : <input type="text" name = "memberPw" value="${member.memberPw }"> <br>
	이름 : <input type="text" name = "memberName" value="${member.memberName }"> <br>
	나이 : <input type="text" name = "memberAge" value="${member.memberAge }"> <br>
	성별 : <input type="text" name = "memberGender" value="${member.memberGender }"> <br>
	이메일 : <input type="text" name = "memberEmail" value="${member.memberEmail }"> <br>
	전화번호 : <input type="text" name = "memberPhone" value="${member.memberPhone }"> <br>
	주소 : <input type="text" name = "memberAddress" value="${member.memberAddress }"> <br> 
	취미 : <input type="text" name = "memberHobby" value="${member.memberHobby }"> <br>
	가입날짜 : <input type="text" name = "memberDate" value="${member.memberDate }"> <br> 
	수정날짜 : <input type="text" name = "updateDate" value="${member.updateDate }"><br>
	회원여부 : <input type="text" name = "memberYn" value="${member.memberYn }">
	 
	</form>
	<a href="/index.jsp"> 메인으로 이동</a>
	<a href="/member/updateView.kh?memberId=${member.memberId }">수정하기</a>
	<a href="/member/delete.kh?memberId=${member.memberId}"> 삭제하기</a>
</body>
</html>