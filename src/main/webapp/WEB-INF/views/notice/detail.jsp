<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<!-- <link rel="stylesheet" href="../resources/css/main.css"> -->
</head>
<body>
	<h1>공지 등록</h1>
<!-- 	<form action="/notice/insert.kh" method="post" enctype="multipart/form-data"> -->
		<ul>
			<li><label>제목</label> <input type="text" name="noticeSubject" value="${notice.noticeSubject}">
			</li>
			<li><label>작성자</label> <input type="text" name="noticeWriter"  value="${notice.noticeWriter}">
			</li>
			<li><label>내용</label> <textarea rows="4" cols="40" name="noticeContent" > ${notice.noticeContent}</textarea>
			</li>
			<li><label>첨부파일</label>
			<img alt="첨부파일" src="/resources/nuploadFiles/${notice.noticeFileRename}"> <br>
			<a href="/resources/nuploadFiles/${notice.noticeFileRename}" download>${notice.noticeFilename }</a>
		 <br>	<input type="file" name="uploadFile">
			</li>
		</ul>


		<div>
		<button onclick="showNoticeList()"> 목록</button>
		<button type="button" onclick="showModifyPage()">수정하기</button>
		<button>삭제하기</button>
<!-- 			<input type="submit" value="등록"> -->
		</div>
<!-- 	</form> -->

	<script>
		function showModifyPage(){
			const noticeNo="${notice.noticeNo}";
			location.href="/notice/modify.kh?noticeNo="+noticeNo;
			
		}
		
		function showNoticeList(){
			location.href="/notice/list.kh";
		}
	</script>
</body>
</html>