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
	
	section div {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.myPage_userInfo {
		color: #80BFB5 !important;
	}
	
	.pwCheck {
		display: flex;
		align-items: center;
	}
	
	.pwCheck input {
		font-size: 16px;
		border-radius: 5px;
		border-width: thin;
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
	
	.pwCheck input:focus {
		background-color: #c8ebe2;
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
				<div>
					<p style="margin-bottom: 10px; color: gray; font-size: 17px;">본인 확인을 위해 비밀번호를 입력해주세요.</p>
					
					<form action="/library/myPage?menu=userInfo&action=pwCheck" method="post" class="pwCheck">
						<input type="password" name="password" required="required">
						<button type="submit" class="btn">확인</button>
					</form>
				</div>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>