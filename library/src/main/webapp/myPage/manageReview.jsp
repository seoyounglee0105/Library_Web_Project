<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
%>

<c:if test="${responseDelete == 1}">
	<% out.println("<script>alert('리뷰가 삭제되었습니다.');</script>"); %>
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
	
	.myPage_manageReview {
		color: #80BFB5 !important;
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
	
	.table_div {
		display: flex;
		justify-content: center;
	}
	
	.deleteReview {
		display: flex;
		justify-content: center;
	}
	
	.deleteReview button {
		border: hidden;
		background: none;
	}
	
	.deleteReview button span:hover {
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
				<h3>리뷰 관리</h3>
				
				<c:choose>
					<c:when test="${userReviewList.size() == 0}">
						<p style="color: gray; font-size: 17px;">작성한 리뷰가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<!-- 리뷰 정보 -->
						<div class="table_div">
							<table class="selectReview" border="1">
								<tr>
									<th>평가</th>
									<th>도서명</th>
									<th>제목</th>
									<th>삭제하기</th>
								</tr>
								<c:forEach var="review" items="${userReviewList}">
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
										<!-- 도서명 -->
										<td><a href="/library/bookDetail?bookId=${review.bookId}" style="color: #21616B;">${review.name}</a></td>
										<!-- 제목 -->
										<td><a href="bookDetail?action=reviewDetail&reviewId=${review.id}" style="color: #21616B;">${review.title}</a></td>
										<!-- 삭제하기 -->
										<td>
											<form class="deleteReview" action="/library/myPage?action=deleteReview" method="post">
												<input type="hidden" name="reviewId" value="${review.id}">
												<button type="submit" onclick="return confirm('삭제하시겠습니까?')"><span style="font-size: 15px; color: #428475;">Click</span></button>
											</form>
										</td>
									</tr>						
								</c:forEach>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</section>
			
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>