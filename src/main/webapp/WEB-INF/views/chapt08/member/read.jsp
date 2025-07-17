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
								<p class="card-description">Read</p>
									<table class="table table-bordered">
										<tr>
											<td>userId</td>
											<td>
												${member.userId }
											</td>
										</tr>
										<tr>
											<td>userName</td>
											<td>
												${member.userName }
											</td>
										</tr>
										<tr>
											<td>auth - 1</td>
											<td>
												${member.authList[0].auth }
											</td>
										</tr>
										<tr>
											<td>auth - 2</td>
											<td>
												${member.authList[1].auth  }
											</td>
										</tr>
										<tr>
											<td>auth - 3</td>
											<td>
												${member.authList[2].auth  }
											</td>
										</tr>
									</table>
									<input class="btn btn-warning" type="button" id="btnUpdate" value="수정"/>
									<input class="btn btn-danger" type="button" id="btnDelete" value="삭제"/>
									<input class="btn btn-primary" type="button" id="btnList" value="목록"
										onclick="javascript:location.href='/crud/member/list'"/>
								</div>
								<form action="/crud/member/remove" method="post" id="delForm">
									<input type="hidden" name="userNo" value="${member.userNo }"/>
								</form>
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
		let btnUpdate = $("#btnUpdate");
		let btnDelete= $("#btnDelete");
		let btnList = $("#btnList");
		let delForm = $("#delForm");
		
		// 수정 버튼 이벤트
		btnUpdate.on("click",function(){
			delForm.attr("action","/crud/member/modify");
			delForm.attr("method","get");
			delForm.submit();
		})
		
		// 삭제 버튼 이벤트
		btnDelete.on("click",function(){
			if(confirm("정말 삭제할까요?")){
				delForm.submit();
			}
		})
		
		// 목록 버튼 이벤트
		btnList.on("click",function(){
			location.href="/crud/member/list";
		})
	})
</script>
</html>