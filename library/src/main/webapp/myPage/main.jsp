<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
%>

<c:choose>
	<c:when test="${responseUpdateInfo == 1}">
		<% out.println("<script>alert('회원 정보가 수정되었습니다.');</script>"); %>
	</c:when>
	
	<c:when test="${responseUpdateInfo == 2}">
		<% out.println("<script>alert('중복된 전화번호입니다.'); history.back();</script>"); %>
	</c:when>
	
	<c:when test="${responseUpdateInfo == 3}">
		<% out.println("<script>alert('전화번호의 형식이 틀렸습니다.'); history.back();</script>"); %>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${responseReview == 1}">
		<% out.println("<script>alert('리뷰 작성이 완료되었습니다.');</script>"); %>
	</c:when>
	
	<c:when test="${responseReview == 2}">
		<% out.println("<script>alert('도서명을 선택해주세요.'); history.back();</script>"); %>
	</c:when>
</c:choose>


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
	
	section h3 a {
		color: #1A606B;
	}
	
	section h3 a:hover {
		color: #80BFB5;
	}

	.user_info tr th {
		padding: 3px 10px 3px 20px;
		text-align: right;
		font-size: 17px;
	}
	
	.user_info_td {
		padding: 3px 0px 3px 10px;
	}
	
	.pen {
		display: flex;
		align-items: center;
	}
	
	.pen img {
		width: 30px;
		height: 30px;
	}
	
	.pen span:hover {
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
				<h3>회원 정보</h3>
				<table class="user_info">
					<tr>
						<th>아이디</th>
						<td><span style="color:#7C7C7C;">ㅣ</span></td>
						<td class="user_info_td">${user.id}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td><span style="color:#7C7C7C;">ㅣ</span></td>
						<td class="user_info_td">${user.name}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><span style="color:#7C7C7C;">ㅣ</span></td>
						<td class="user_info_td">${user.phoneNumber.substring(0, 9)}****</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><span style="color:#7C7C7C;">ㅣ</span></td>
						<td class="user_info_td">
							<c:if test="${user.email == null}"><span style="color:gray;">등록되지 않음</span></c:if>
							${user.email}
						</td>
					</tr>
				</table>
		
				<hr style="margin: 30px 0;">
				<h3><a href="/library/myPage?menu=myBooks">대여 중인 도서</a></h3>
					<!-- 대여 중인 도서가 없다면 -->
					<c:if test="${checkoutCount == 0}">
						<p style="color: gray; font-size: 17px;">대여 중인 도서가 없습니다.</p>						
					</c:if>
					<!-- 대여 중인 도서가 있다면 -->
					<c:if test="${checkoutCount != 0}">
						<p style="color: gray; font-size: 17px;">${checkoutCount}권의 도서를 대여 중입니다.</p>						
					</c:if>
					
				<hr style="margin: 30px 0;">
					<!-- 리뷰 작성 가능한 도서가 없다면 -->
					<c:if test="${reviewCount == 0}">
						<h3>리뷰 작성</h3>
						<p style="color: gray; font-size: 17px;">작성 가능한 도서가 없습니다.</p>						
					</c:if>
					<!-- 리뷰 작성 가능한 도서가 있다면 -->
					<c:if test="${reviewCount != 0}">
						<h3><a href="/library/myPage?menu=writeReview" class="pen">
								<span style="color: #1A606B;">리뷰 작성&nbsp;</span>
								<img src="/library/images/pen.png">
							</a>
						</h3>
						<p style="color: gray; font-size: 17px;">작성 가능한 ${reviewCount}권의 도서가 있습니다.</p>						
					</c:if>								
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>