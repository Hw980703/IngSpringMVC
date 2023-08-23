<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<!-- <link rel="stylesheet" href="../resources/css/main.css"> -->
</head>
<body>
	<h1>공지 수정</h1>
	<form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
	
<!-- 		수정할 때, 리다이렉트 할 때 사용됨 -->
		<input type="hidden" name ="noticeNo" value="${notice.noticeNo }">
<!-- 		기존 업로드 된 파일 체크 할 때 사용 -->
<!-- 			수정 할 때 파일을 안바꾸면 데이터를 그대로 넣어줘야하기에 hidden 으로 세가지 데이터 부여 -->
		<input type="hidden" name ="noticeFilename" value="${notice.noticeFilename}">
		<input type="hidden" name ="noticeFileRename" value="${notice.noticeFileRename}">
		<input type="hidden" name ="noticeFilepath" value="${notice.noticeFilepath}">
		<input type="hidden" name ="noticeFilelength" value="${notice.noticeFilelength}">
		
		<ul>
			<li><label>제목</label> <input type="text" name="noticeSubject" value = "${notice.noticeSubject }">
			</li>
			<li><label>작성자</label> <input type="text" name="noticeWriter" value = "${notice.noticeWriter }">
			</li>
			<li><label>내용</label> <textarea rows="4" cols="40" name="noticeContent"> ${notice.noticeContent }</textarea>
			</li>
			<li><label>첨부파일</label>
			<img alt="첨부파일" src="/resources/nuploadFiles/${notice.noticeFilename }"> <br>
			<a href="/resources/nuploadFiles/${notice.noticeFilename}" download>${notice.noticeFilename }</a>
		 <br>	<input type="file" name="uploadFile">
			</li>
			</li>
		</ul>

		<div>
			<input type="submit" value="수정">
		</div>
	</form>
</body>
</html>