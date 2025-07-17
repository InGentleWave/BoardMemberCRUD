<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DDIT MEMBER</title>
<%@ include file="../../skydash/headPart.jsp"%>
</head>
<body>
	<div class="container-scroller">
		<%@ include file="../../skydash/header.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@ include file="../../skydash/aside.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Chapt08</h4>
								</div>
								<div class="card-body">
									<p class="card-description">Read</p>
									<form action="/crud/member/modify" method="post" id="member">
										<input type="hidden" name="userNo" value="${member.userNo }" />
										<table class="table table-bordered">
											<tr>
												<td>userId</td>
												<td><input type="text" name="userId"
													value="${member.userId }" readonly /></td>
											</tr>
											<tr>
												<td>userName</td>
												<td>
													<input type="text" name="userName" id="userName" value="${member.userName }" />
												</td>
											</tr>
											<c:forEach begin="0" end="2" step="1" var="idx">
											<tr>
												<td>auth - ${idx +1}</td>
												<td>
													
													<select name="authList[${idx }].auth">
														<option value="">---선택해주세요---</option>
														<option value="ROLE_USER" <c:if test="${member.authList[idx].auth eq 'ROLE_USER' }">selected</c:if> >사용자</option>
														<option value="ROLE_MEMBER"<c:if test="${member.authList[idx].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option>
														<option value="ROLE_ADMIN"<c:if test="${member.authList[idx].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option>
													</select>
												</td>
											</tr>
											</c:forEach>
<!-- 											<tr> -->
<!-- 												<td>auth - 1</td> -->
<!-- 												<td> -->
													
<!-- 													<select name="authList[0].auth"> -->
<!-- 														<option value="">---선택해주세요---</option> -->
<%-- 														<option value="ROLE_USER" <c:if test="${member.authList[0].auth eq 'ROLE_USER' }">selected</c:if> >사용자</option> --%>
<%-- 														<option value="ROLE_MEMBER"<c:if test="${member.authList[0].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option> --%>
<%-- 														<option value="ROLE_ADMIN"<c:if test="${member.authList[0].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option> --%>
<!-- 													</select> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td>auth - 2</td> -->
<!-- 												<td> -->
<!-- 													<select name="authList[1].auth"> -->
<!-- 														<option value="">---선택해주세요---</option> -->
<%-- 														<option value="ROLE_USER" <c:if test="${member.authList[1].auth eq 'ROLE_USER' }">selected</c:if> >사용자</option> --%>
<%-- 														<option value="ROLE_MEMBER"<c:if test="${member.authList[1].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option> --%>
<%-- 														<option value="ROLE_ADMIN"<c:if test="${member.authList[1].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option> --%>
<!-- 													</select>	 -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td>auth - 3</td> -->
<!-- 												<td> -->
<!-- 													<select name="authList[2].auth"> -->
<!-- 														<option value="">---선택해주세요---</option> -->
<%-- 														<option value="ROLE_USER" <c:if test="${member.authList[2].auth eq 'ROLE_USER' }">selected</c:if> >사용자</option> --%>
<%-- 														<option value="ROLE_MEMBER"<c:if test="${member.authList[2].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option> --%>
<%-- 														<option value="ROLE_ADMIN"<c:if test="${member.authList[2].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option> --%>
<!-- 													</select> -->
<!-- 												</td> -->
<!-- 											</tr> -->
										</table>
									</form>
									<input class="btn btn-warning" type="button" id="btnModify" value="수정" />
									<input class="btn btn-primary" type="button" id="btnCancle" value="취소" />
								</div>
								<form action="/crud/member/remove" method="post" id="delForm">
									<input type="hidden" name="userNo" value="${member.userNo }" />
								</form>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../../skydash/footer.jsp"%>
			</div>
		</div>
	</div>

	<%@ include file="../../skydash/footerPart.jsp"%>
</body>
<script type="text/javascript">
	$(function() {
		let member = $("#member");
		let btnModify = $("#btnModify");
		let btnCancle = $("#btnCancle");
		
		// 수정 버튼 이벤트
		btnModify.on("click", function() {
			let userName = $("#userName").val();
			
			if(userName == null || userName==''){
				alert("이름을 입력해주세요!");
				return false;
			}
			member.submit();
		})

		// 삭제 버튼 이벤트
		btnCancle.on("click", function() {
			location.href="/crud/member/read?userNo=${member.userNo}";
		})

	})
</script>
</html>