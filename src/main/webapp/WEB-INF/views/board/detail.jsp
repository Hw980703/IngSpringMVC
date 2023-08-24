<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<!-- <link rel="stylesheet" href="../resources/css/main.css"> -->
<style>
table{
margin:0px;
}
</style>
</head>
<body>
	<h1>공지 등록</h1>
<!-- 	<form action="/notice/insert.kh" method="post" enctype="multipart/form-data"> -->
		<ul>
			<li><label>제목</label> <input type="text" name="noticeSubject" value="${board.boardTitle}">
			</li>
			<li><label>작성자</label> <input type="text" name="noticeWriter"  value="${board.boardWriter}">
			</li>
			<li><label>내용</label> <textarea rows="4" cols="40" name="noticeContent" > ${board.boardContent}</textarea>
			</li>
			<li><label>첨부파일</label>
			<img alt="첨부파일" src="/resources/buploadFiles/${board.boardFilerename}"> <br>
			<a href="/resources/buploadFiles/${board.boardFilerename}" download>${board.boardFilename }</a>
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

		<form action="/board/addReply.kh" method="post">
			<textarea rows="3" cols="55"></textarea>
			<input type="submit" value="완료">
		</form>
		<table align="center" width="400" border="1">
			<tr>
					<td>일용자</td>
					<td>아 처음이시군요 ㅋㅋ</td>
					<td>2023/08/24</td>
					
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
			</tr>
			<tr>
					<td>이용자</td>
					<td>아 두번째이시군요 ㅋㅋ</td>
					<td>2023/08/24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
			</tr>
			<tr>
					<td>삼용자</td>
					<td>아 세번째이시군요 ㅋㅋ</td>
					<td>2023/08/24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
			</tr>
			
		
		</table>
		

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