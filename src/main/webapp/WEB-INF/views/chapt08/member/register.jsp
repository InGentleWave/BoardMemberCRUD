<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT MEMBER</title>
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
								<form action="/crud/member/register" method="post" id="member">
									<table class="table table-bordered">
										<tr>
											<td>userId</td>
											<td>
												<input type="text" name="userId" id="userId" />
											</td>
										</tr>
										<tr>
											<td>userPw</td>
											<td>
												<input type="text" name="userPw" id="userPw" />
											</td>
										</tr>
										<tr>
											<td>userName</td>
											<td>
												<input type="text" name="userName" id="userName" />
											</td>
										</tr>
									</table>
									<input type="button" id="btnRegister" value="Register"/>
									<input type="button" id="btnList" value="List"/>
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
		let member = $("#member");
		
		// 등록 버튼 이벤트
		btnRegister.on("click",function(){
			let userId = $("#userId").val();		// 제목 값
			let userPw = $("#userPw").val();	// 작성자 값
			let userName = $("#userName").val();	// 내용 값
			
			if(userId == null || userId == ""){
			 	alert("아이디를 입력해주세요.");
			 	return false;
			}
			
			if(userPw == null || userPw == ""){
			 	alert("비밀번호를 입력해주세요.");
			 	return false;
			}
			
			if(userName == null || userName == ""){
			 	alert("이름을 입력해주세요.");
			 	return false;
			}
			
			member.submit();
		})
		
		// 목록 버튼 이벤트
		btnList.on("click",function(){
			location.href="/crud/member/list";
		})
	})
</script>
</html>