<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");

	// ${book}으로 targetBookDto 가져올 수 있음
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
	
	.book, .table_div {
		width: 100%;
		display: flex;
		justify-content: center;
	}
	
	.book_info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.book_name {
		font-size: 37px;
		margin-bottom: 30px;
		border-bottom: 5px solid #EEEEEE;
	}
	
	.selectReview {
		border-collapse: collapse;
	}
	
	.selectReview th, .selectReview td {
		padding: 3px 25px;
		border-left: hidden;
		border-right: hidden;
	}
	
	.selectReview th {
		color: #204247;
		background-color: buttonhighlight;
	}
	
	.selectReview td a:hover {
		color: #80BFB5 !important;
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
				
				<div class="book">
					<div class="book_info">
						<p>
							<span style="color: #F5CF14; margin-right: 5px;'">
								<c:choose>
									<c:when test="${imgStar == 1}">
										★☆☆☆☆
									</c:when>
									<c:when test="${imgStar == 2}">
										★★☆☆☆
									</c:when>
									<c:when test="${imgStar == 3}">
										★★★☆☆
									</c:when>
									<c:when test="${imgStar == 4}">
										★★★★☆
									</c:when>
									<c:when test="${imgStar == 5}">
										★★★★★
									</c:when>
								</c:choose>
							</span>
							<!-- 평균 점수 -->
							<span style="color: gray;">
								(${avgStar})
							</span>
						</p>
						<p class="book_name">${book.name}</p>
						<br>
					</div>
				</div>
				<!-- 리뷰 정보 -->
				<div class="table_div">
					<table class="selectReview" border="1">
						<tr>
							<th>평가</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일자</th>
						</tr>
						<c:forEach var="review" items="${reviewList}">
							<tr>
								<!-- 별점 -->
								<td> <span style="color: #F5CF14;">
									<c:choose>
										<c:when test="${review.star == 5}">
											★★★★★
										</c:when>
										<c:when test="${review.star == 4}">
											★★★★☆
										</c:when>
										<c:when test="${review.star == 3}">
											★★★☆☆
										</c:when>
										<c:when test="${review.star == 2}">
											★★☆☆☆
										</c:when>
										<c:when test="${review.star == 1}">
											★☆☆☆☆
										</c:when>
									</c:choose>
								</span> </td>
								<!-- 작성자 -->
								<td>${review.userId}</td>
								<!-- 제목 -->
								<td><a href="bookDetail?action=reviewDetail&reviewId=${review.id}" style="color: #21616B;">${review.title}</a></td>
								<!-- 작성일자 -->
								<td>${review.writeDate}</td>
							</tr>						
						</c:forEach>
					</table>
				</div>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->
</body>
</html>