<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
	String menu = request.getParameter("menu");
%>
	<!-- 입력한 전화번호가 틀렸다면 -->
	<c:if test="${result == 0}">
		<% out.print("<script>alert('비밀번호가 틀렸습니다.');</script>"); %>		
	</c:if>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/category.css">
<style type="text/css">

	section h3 {
		margin-bottom: 30px;
		color: #1A606B; 
		font-size: 20px;
	}
	
	.myPage_userInfo {
		color: #80BFB5 !important;
	}
	
	.info_update {
		margin-left: 30px;
		width: 400px;
	}
	
	.form-group {
		display: flex;
		margin-bottom: 5px;
	}
	
	.form-group label {
		flex: 1;
		font-size: 18px;
	}
	
	.form-group input {
		flex: 2;
		font-size: 16px;
	}
	
	.form-group input:focus {
		background-color: #c8ebe2;
	}
	
	.form-btn {
		display: flex;
		justify-content: center;
		margin-top: 20px;
	}
	
	.btn {
		font-size: 15px;
		line-height: 0;
		border-radius: 8px;
		border-width: thin;
		height: 28px;
		width: 50px;
		text-align: center;
		margin-left: 10px;
		background-color: #B0DFD3;
	}

	.btn:hover {
		background-color: #80BFB5;
		cursor: pointer;
	}
	
	.delete_user_div {
		display: flex;
		justify-content: right;
	}
	
	.delete_user {
		color: red;
	}
	
	.delete_user:hover {
		color: #8F000C !important;
	}
	
	.form_div {
	 	width: 100%;
	 	display: flex;
	 	justify-content: center;
	}
	
</style>
</head>
<body>
	<div class="page-container">
		<!-- 상단 메뉴 -->
		<jsp:include page="../layout/header_login.jsp"/>
		
		<div class="main-container">
			<!-- 카테고리 -->
			<jsp:include page="../layout/category_myPage.jsp"/>	
				
			<section style="flex-basis: 800px;">
				<h3>회원 정보 수정</h3>
				<div class="form_div">
					<form action="/library/myPage?menu=userInfo&action=updateInfo" method="post" class="info_update">
						<div class="form-group">
							<label for="id">아이디 *</label>
							<!-- 수정 불가 -->
							<input name="id" id="id" type="text" required="required" value="${user.id}" readonly="readonly">					
						</div>
						<div class="form-group">
							<label for="pw">비밀번호 *</label>
							<input name="password" id="pw" type="password" required="required" value="${user.password}">					
						</div>
						<div class="form-group">
							<label for="userName">이름 *</label>
							<input name="userName" id="userName" type="text" required="required" value="${user.name}">					
						</div>
						<div class="form-group">
							<label for="phoneNumber">전화번호 *</label>
							<input name="phoneNumber" id="phoneNumber" type="tel" required="required" value="${user.phoneNumber}">					
						</div>
						<div class="form-group">
							<label for="address">주소 *</label>
							<input name="address"  id="address" type="text" required="required" value="${user.address}">					
						</div>
						<div class="form-group">
							<label for="email">이메일</label>
							<input name="email"  id="email" type="email" value="${user.email}">					
						</div>
						<div class="form-btn">
							<input class="btn"  name="signUp"  type="submit" value="완료" onclick="return confirm('입력한 정보대로 수정하시겠습니까?')">
						</div>
					</form>		
				</div>
				<div class="delete_user_div">
					<a href="/library/myPage?action=deleteUser" onclick="return confirm('정말로 탈퇴하시겠습니까?')" class="delete_user">회원 탈퇴</a>
				</div>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>