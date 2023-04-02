<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
	String menu = request.getParameter("menu");
%>

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
	
	.myPage_updateInfo {
		color: #80BFB5 !important;
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
				
			<section>
				<h3>회원 정보 수정</h3>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>