<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");

	// ${book}으로 targetBookDto 가져올 수 있음
	
	request.setCharacterEncoding("UTF-8");
	
	%>
	
<c:if test="${action == 'checkout'}">
	<%
		out.println("<script>alert('대여되었습니다.');</script>");
	%>
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

	.book {
		display: flex;
		align-items: flex-start;
		justify-content: flex-start;
	}
	/* 너비 3 : 높이 4 */
	.book_image {
		width: 300px;
		height: 400px;
		border: 3px solid #3F3F3F;
		margin-right: 40px;
		margin-bottom: 40px;
	}
	
	.book_name {
		font-size: 37px;
		margin-top: 20px;
		margin-bottom: 15px;
		border-bottom: 5px solid #EEEEEE;
	}
	
	.book_info {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: flex-start;
		font-size: 18px;
	}
	
	.check_out_btn {
		margin-top: 10px;
		font-size: 25px;
		width: 110px;
	}
	
	.review {
		color: #1A606B;
		font-size: 17px;
	}
	
	.review_span {
		color: #1A606B;
		font-size: 17px;
	}
	
	.review:hover {
		color: #80BFB5;
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

			<section>
				<h3>상세 페이지</h3>
				
				<div class="book">
					<img class="book_image" src="/library/${book.image}">
					<div class="book_info">
						<p class="book_name">${book.name}</p>
						<p class="book_category">카테고리 : ${book.categoryName}</p>
						<p> <span class="book_writer">지은이 : ${book.writer}</span>
							<span class="split">ㅣ</span>
							<span class="book_publisher">출판사 : ${book.publisher}</span>
						</p>
						<br>
						<p>
							<c:choose>
								<c:when test="${reviewList.size() == 0}">
									<span class="review_span">리뷰 ${reviewList.size()}개</span>
								</c:when>
								<c:otherwise>
									<a href="/library/bookDetail?bookId=${book.id}&action=selectReview" class="review">리뷰 ${reviewList.size()}개 보기</a>
								</c:otherwise>
							</c:choose>
							<span style="color: #F5CF14; margin: 0 5px;'">
								<c:choose>
									<c:when test="${imgStar == 0}">
										☆☆☆☆☆
									</c:when>
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
							<c:choose>
								<c:when test="${avgStar == 0.00}">
									<span style="color: gray; font-size:15px;">
										(평가 없음)
									</span>
								</c:when>
								<c:otherwise>
									<span style="color: gray;">
										(${avgStar})
									</span>
								</c:otherwise>
							</c:choose>
						</p>
						<br>
						<div class="check_out">
							<div>
								<p class="book_count">총 대여 횟수 : ${book.checkOutCount}회</p>
								<p class="book_available">
									대여 가능 여부 :
									<c:choose>
										<c:when test="${book.isAvailable == true}">
											<span style="color: blue">가능</span>
										</c:when>
										<c:otherwise>
											<span style="color: red">불가능</span>
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<br>
							<c:if test="${book.isAvailable == true && id != null}">
								<form class="check_out_form" action="/library/bookDetail?bookId=${book.id}&action=checkout" method="post">
									<input type="hidden" name="bookId" value="${book.id}">
									<button class="check_out_btn" type="submit" onclick="return confirm('대여하시겠습니까?')">대여하기</button>
								</form>
							</c:if>
							<c:if test="${book.isAvailable == true && id == null}">
								<p style="color: gray;"> 로그인 후 대여 가능합니다.</p>
							</c:if>
						</div>
					</div>
				</div>
				<hr style="margin-bottom: 10px;">
				<h3 style="color: #1A606B; font-size: 20px;">책 소개</h3>
				
				<p class="book_description">${book.description}</p>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->
</body>
</html>