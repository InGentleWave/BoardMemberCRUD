<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT BOARD</title>
<%@ include file="../../skydash/headPart.jsp" %>
</head>
<body>
	<div class="container-scroller">
		<%@ include file="../../skydash/header.jsp" %>
		<div class="container-fluid page-body-wrapper">
			<%@ include file="../../skydash/aside.jsp" %>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Chapt08</h4>
								</div>									
								<div class="card-body">
								<p class="card-description">Register</p>
								<form action="/crud/board/register" method="post" id="board">
									<c:set value="등록" var="btnText"/>
									<c:if test="${status eq 'u' }">
										<input type="hidden" name="boardNo" value="${board.boardNo }"/>
										<c:set value="수정" var="btnText"/>
									</c:if>
									<table class="table table-bordered">
										<tr>
											<td>제목</td>
											<td>
												<input type="text" name="title" id="title" value="${board.title }"/>
											</td>
										</tr>
										<tr>
											<td>작성자</td>
											<td>
												<input type="text" name="writer" id="writer" value="${board.writer }"/>
											</td>
										</tr>
										<tr>
											<td>내용</td>
											<td>
												<textarea  rows="5" cols="20" name="content" id="content">${board.content }</textarea>
											</td>
										</tr>
									</table>
									<input type="button" id="btnRegister" value="${btnText }"/>
									<input type="button" id="btnList" value="목록"/>
								</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../../skydash/footer.jsp" %>
			</div>
		</div>
	</div>

	<%@ include file="../../skydash/footerPart.jsp" %>
</body>
<script type="text/javascript">
	$(function(){
		let btnRegister = $("#btnRegister");
		let btnList= $("#btnList");
		let board = $("#board");
		
		// 등록 버튼 이벤트
		btnRegister.on("click",function(){
			let title = $("#title").val();		// 제목 값
			let writer = $("#writer").val();	// 작성자 값
			let content = $("#content").val();	// 내용 값
			
			if(title == null || title == ""){
			 	alert("제목을 입력해주세요.");
			 	return false;
			}
			
			if(writer == null || title == ""){
			 	alert("작성자를 입력해주세요.");
			 	return false;
			}
			
			if(content == null || title == ""){
			 	alert("내용을 입력해주세요.");
			 	return false;
			}
			if($(this).val()=='수정'){
				board.attr("action","/crud/board/modify");
			}
			
			board.submit();
		})
		
		// 목록 버튼 이벤트
		btnList.on("click",function(){
			location.href="/crud/board/list";
		})
	})
</script>
</html>