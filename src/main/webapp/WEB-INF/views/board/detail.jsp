<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<!-- <link rel="stylesheet" href="../resources/css/main.css"> -->
<style>
table {
	margin: 0px;
}
</style>
</head>
<body>
	<h1>공지 등록</h1>
	<!-- 	<form action="/notice/insert.kh" method="post" enctype="multipart/form-data"> -->
	<ul>
		<li><label>제목</label> <input type="text" name="noticeSubject"
			value="${board.boardTitle}"></li>
		<li><label>작성자</label> <input type="text" name="noticeWriter"
			value="${board.boardWriter}"></li>
		<li><label>내용</label> <textarea rows="4" cols="40"
				name="noticeContent"> ${board.boardContent}</textarea></li>
		<li><label>첨부파일</label> <img alt="첨부파일"
			src="/resources/buploadFiles/${board.boardFilerename}"> <br>
			<a href="/resources/buploadFiles/${board.boardFilerename}" download>${board.boardFilename }</a>
			<br> <input type="file" name="uploadFile"></li>
	</ul>


	<div>
		<button onclick="showNoticeList()">목록</button>
		<button type="button" onclick="showModifyPage()">수정하기</button>
		<button>삭제하기</button>
		<!-- 			<input type="submit" value="등록"> -->
	</div>
	<!-- 	</form> -->

	<form action="/reply/add.kh" method="post">
		<input type="hidden" name="refBoardNo" value="${board.boardNo}">
		<table width="500" border="1">
			<tr>
				<td></td>
				<textarea rows="3" cols="55" name="replyContent"></textarea>
				<td><input type="submit" value="완료"></td>
			</tr>
		</table>
	</form>
	<table align="center" width="400" border="1">
		<c:forEach var="reply" items="${rList}">



			<tr>
				<td>${reply.replyWriter}</td>
				<td>${reply.replyContent }</td>
				<td>${reply.rCreateDate }</td>

				<td><a href="javascript:void(0)" onclick="showModifyForm(this);">수정하기</a>
					<a href="#">삭제하기</a></td>
			</tr>
			<tr>
			<td id="replyModifyForm" style="display:none;">
<!-- 			<form action="/reply/update.kh" method="post"> -->
<%-- 			<input type="hidden" name="replyNo" value="${reply.replyNo}"> --%>
<%-- 			<input type="hidden" name="refBoardNo" value="${reply.refBoardNo}"> --%>
<!-- 			</form> -->
				<td colspan="3"><input id="replyContent" type="text" size="50" value="${reply.replyContent}" name="replyContent"></td>
				<td><input type="submit" value="완료" onclick = "replyModify('${reply.replyNo}','${reply.refBoardNo}')"></td>
			</tr>
		</c:forEach>
	</table>


	<script>
		function showModifyPage() {
			const noticeNo = "${notice.noticeNo}";
			location.href = "/notice/modify.kh?noticeNo=" + noticeNo;
		}

		function showNoticeList() {
			location.href = "/notice/list.kh";
		}
		function showModifyForm(obj) {
			//2.두번째 방법 DOM프로그래밍을 이용하는 방법
// 			<tr id="replyModifyForm" style="display: none;">
// 				<td colspan="3"><input type="text" size="50"
// 					value="${reply.replyContent}"></td>
// 				<td><input type="button" value="완료"></td>
// 			</tr>
// 			const trTag = document.createElement("tr");
// 			const tdTag1 = document.createElement("td");
// 			tdTag1.colSpan = 3;
// 			const inputTag1 = document.createElement("input");
// 			inputTag1.type="text";
// 			inputTag1.size=50;
// 			inputTag1.value = "111111";
// 			tdTag1.appendChild(inputTag1);
// 			const tdTag2 = document.createElement("td");
// 			const inputTag2 = document.createElement("input");
// 			inputTag2.type="button";
// 			inputTag2.value="완료";
// 			tdTag2.appendChild(inputTag2);
// 			trTag.appendChild(tdTag1);
// 			trTag.appendChild(tdTag2);
// 			console.log(trTag);
// 			// 클릭한 a를 포함하고 있는 tr 다음에 수정폼이 있는 tr 추가하기
// 			const prevTrTag = obj.parentElement.parentElement;
// 			if(!prevTrTag.nextElementSibling == null || !prevTrTag.nextElementSibling.querySelector("input")){
// 			prevTrTag.parentNode.insertBefore(trTag,prevTrTag.nextSibling);
// 			}
			//1.첫번째 방법
		
			//document.querySelector("#replyModifyForm").style.display="";
			alert("test");
			obj.parentElement.parentElement.nextElementSibling.style.display="";
			
		}
			
		function replyModify(obj , replyNo,refBoardNo){
			const form = document.createElement("form");
			form.action = "/reply/update.kh";
			form.method = "post";
			const input = document.createElement("input");
			input.type = "hidden";
			input.value = replyNo;
			input.name = "replyNo";
			const input2 = document.createElement("input");
			input2.type="hidden";
			input2.value= refBoardNo;
			input2.name = "refBoardNo";
			const input3 = document.createElement("input");
			input3.type="text";
// 			input3.value= document.querySelector("#replyContent").value;
			input3.value= obj;
			input3.name = "replyContent";
			form.appendChild(input);
			form.appendChild(input2);
			form.appendChild(input3);
			document.body.appendChild(form);
			form.submit();
			
		}
			</script>
</body>
</html>