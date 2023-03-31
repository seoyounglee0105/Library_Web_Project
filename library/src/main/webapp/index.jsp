<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/category.css">
<style>
</style>
</head>
<body>
	<div class="page-container">
		<!-- 상단 메뉴 -->
		<% if (id == null) { %>
			<jsp:include page="layout/header.jsp"/>
		<% } else { %>	
			<!-- 로그인된 상태라면 -->
			<jsp:include page="layout/header_login.jsp"/>
		<% } %>
		
		<div class="main-container">
		
			<!-- 카테고리 -->
			<jsp:include page="layout/category.jsp"/>			
			
			<section>sdsdsad</section>
		</div>
	</div>
	<!-- end of page-container -->

</body>
</html>