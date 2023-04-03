<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
	String menu = request.getParameter("menu");
%>

<c:if test="${result == 1}">
	<% out.print("<script>alert('반납 처리되었습니다.');</script>"); %>
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
	
	.myPage_myBooks {
		color: #80BFB5 !important;
	}
	
	.checkout {
		border-collapse: collapse;
	}
	
	.checkout th, .checkout td {
		padding: 3px 25px;
		border-left: hidden;
		border-right: hidden;
	}
	
	.checkout th {
		color: #204247;
		background-color: buttonhighlight;
	}
	
	.checkout td a:hover {
		color: #80BFB5 !important;
	}

	.returnBook {
		display: flex;
		justify-content: center;
	}
	
	.returnBook button {
		border: hidden;
		background: none;
	}
	
	.returnBook button span:hover {
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
				<h3>대여 중인 도서</h3>
					<!-- 대여 중인 도서가 없다면 -->
					<c:if test="${notReturnList.size() == 0}">
						<p style="color: gray; font-size: 17px;">대여 중인 도서가 없습니다.</p>						
					</c:if>
					<!-- 대여 중인 도서가 있다면 -->
					<c:if test="${notReturnList.size() != 0}">
						<table class="checkout" border="1">
							<tr>
								<th>대여 번호</th>
								<th>도서명</th>
								<th>저자명</th>
								<th>대여 일자</th>
								<th>반납하기</th>
							</tr>
							<c:forEach var="dto" items="${notReturnList}">
								<tr>
									<td style="text-align: center">${dto.id}</td>
									<!-- 책 이름 클릭하면 상세 페이지로 이동 -->
									<td><a href="/library/bookDetail?bookId=${dto.bookId}" style="color: #21616B;">${dto.name}</a></td>
									<td style="text-align: center">${dto.writer}</td>
									<td style="text-align: center">${dto.checkoutDate.substring(0, 10)}</td>
									<td>
										<form class="returnBook" action="/library/myPage?menu=myBooks&action=returnBook" method="post">
											<input type="hidden" name="checkoutId" value="${dto.id}">
											<button type="submit" onclick="return confirm('반납하시겠습니까?')"><span style="font-size: 15px; color: #428475;">Click</span></button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				<h3 style="margin-top: 60px">대여 내역</h3>
					<!-- 대여 내역이 없다면 -->
					<c:if test="${allList.size() == 0}">
						<p style="color: gray; font-size: 17px;">대여 내역이 없습니다.</p>						
					</c:if>
					<!-- 대여 내역이 있다면 -->
					<c:if test="${allList.size() != 0}">
						<table class="checkout" border="1">
							<tr>
								<th>대여 번호</th>
								<th>도서명</th>
								<th>저자명</th>
								<th>대여 일자</th>
								<th>반납 여부</th>
							</tr>
							<c:forEach var="dto" items="${allList}">
								<tr>
									<td style="text-align: center">${dto.id}</td>
									<!-- 책 이름 클릭하면 상세 페이지로 이동 -->
									<td><a href="/library/bookDetail?bookId=${dto.bookId}" style="color: #21616B;">${dto.name}</a></td>
									<td style="text-align: center">${dto.writer}</td>
									<td style="text-align: center">${dto.checkoutDate.substring(0, 10)}</td>
									<td style="text-align: center">
										<c:choose>
											<c:when test="${dto.isReturn == true}">
												<span style="color: blue;">완료</span>
											</c:when>
											<c:otherwise>
												<span style="color: red;">미완료</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>