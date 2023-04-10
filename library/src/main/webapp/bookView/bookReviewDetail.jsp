<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.young.lib.service.ReviewService"%>
<%@page import="com.young.lib.dto.ReviewDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<style type="text/css">

	section h3 {
		margin-bottom: 30px;
		color: #1A606B; 
		font-size: 20px;
	}
	
	.split {
		line-height: 0;
		font-weight: 300;
	}
	
	.book_name {
		color: #1A606B;
	}
	
	.book_name:hover {
		color: #80BFB5;
	}
	
	.review_div {
		padding: 0 30px;
		width: 100%;
	}
	
	.content {
		font-size: 17px;
		padding: 5px;
		resize: none;
	}

</style>
</head>
<body>
	<div class="page-container">
		<!-- 상단 메뉴 -->
		<%
		if (id == null) {
		%>
		<jsp:include page="../layout/header.jsp" />
		<%
		} else {
		%>
		<!-- 로그인된 상태라면 -->
		<jsp:include page="../layout/header_login.jsp" />
		<%
		}
		%>

		<div class="main-container">

			<!-- 카테고리 -->
			<jsp:include page="../layout/category.jsp" />

			<section style="flex-basis: 900px;">
				<h3>리뷰 페이지</h3>
				<div class="review_div">
					<div>
						<p>
							<span style="color: #F5CF14;">
								<c:choose>
									<c:when test="${review.star == 1}">
										★☆☆☆☆
									</c:when>
									<c:when test="${review.star == 2}">
										★★☆☆☆
									</c:when>
									<c:when test="${review.star == 3}">
										★★★☆☆
									</c:when>
									<c:when test="${review.star == 4}">
										★★★★☆
									</c:when>
									<c:when test="${review.star == 5}">
										★★★★★
									</c:when>
								</c:choose>
							</span>
							<span class="split">ㅣ</span>
							<a href="/library/bookDetail?bookId=${review.bookId}" class="book_name">${bookName}</a>
						</p>
					</div>
					<div>
						<h1 style="font-size:30px; margin-bottom: 8px;">${review.title}</h1>
					</div>
					<div style="margin-bottom: 40px;">
						<span style="margin-right: 5px; font-weight: 600;">작성자</span>
						<span>${review.userId}</span>
						<span class="split">ㅣ</span>
						<span style="margin-right: 5px; font-weight: 600;">작성일자</span>
						<span>${review.writeDate}</span>
					</div>
					<div>
						<c:choose>
							<c:when test="${imageName != null}">
								<textarea cols="56" rows="10" readonly="readonly" class="content">${review.content}</textarea>								
								<img alt="리뷰 이미지" src="/library/images/uploadImages/${imageName}" style="width: 212px; height: 262px; margin-left: 30px; border:1px solid gray">			
							</c:when>
							<c:otherwise>
								<textarea cols="83" rows="10" readonly="readonly" class="content">${review.content}</textarea>								
							</c:otherwise>
						</c:choose>
					
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->
</body>
</html>