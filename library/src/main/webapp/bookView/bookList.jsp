<%@page import="com.young.lib.dto.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");
	
	String menu = request.getParameter("menu");
	String orderby = request.getParameter("orderby");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/category.css">
<style>

	/* 선택된 메뉴에 글자 색 적용 */  
	<%if ("all".equals(menu)) {%>  
		.cate_all { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("it".equals(menu)) {%> 
		.cate_it { 
			color: #80BFB5 !important; 
		}
	
	<%} else if ("novel".equals(menu)) {%> 
		.cate_novel { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("hobby".equals(menu)) {%> 
		.cate_hobby { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("economy".equals(menu)) {%> 
		.cate_economy { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("improvement".equals(menu)) {%> 
		.cate_improvement { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("cook".equals(menu)) {%> 
		.cate_cook { 
			color: #80BFB5 !important;
		}
	
	<%} else if ("child".equals(menu)) {%>  
		.cate_child {  
			color: #80BFB5 !important;
		}
	
	<%}%>
	
	/* 정렬 버튼 */ 
	<% if ("bookName".equals(orderby)) {%>
		.orderButton li:nth-of-type(1) a {
			color: #80BFB5 !important; 
		}
		
	<%} else if ("index".equals(orderby)) {%>
		.orderButton li:nth-of-type(2) a {
			color: #80BFB5 !important;
		}
	
	<%} else if ("count".equals(orderby)) {%>
		.orderButton li:nth-of-type(3) a {
			color: #80BFB5 !important;
		}
	
	<%}%>
	
	.right_ul li a:action { 
	color: #C6EBDE;
}
	
	.orderButton {
		display: flex;
		margin-bottom: 30px;
	}
	
	.orderButton li {
		margin-right: 20px;
	}
	
	.orderButton li a {
		font-size: 20px;
		color: #1A606B;
	}
	
	.orderButton li a:hover {
		color: #80BFB5;
	}
	
	.book {
		display: flex;
		margin: 30px 0;
	}
	
	.book:first-of-type {
		margin-top: 0;
	}
	
	.book:last-of-type {
		margin-bottom: 0;
	}
	
	.bookImage {
		/* 너비 3 : 높이 4 */
		width: 180px;
		height: 240px;
		border: 3px solid #3F3F3F;
		margin-right: 30px;
	}
	
	.book_info {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: flex-start;
		width: 800px;
	}
	
	.book_category {
		margin-top: 6px;
		font-size: 14px;
		color: #1A606B;
		margin-left: 4px;
	}
	
	.book_name {
		font-size: 40px;
		line-height: 0;
		margin-top: 20px;
		margin-bottom: 35px;
	}

	.book_writer, .book_available {
		font-size: 18px;
		padding: 0 7px 0 4px;
	}
	
	.book_publisher {
		font-size: 18px;
		padding: 0 10px 0 7px;		
	}
	
	.split {
		line-height: 0;
		font-weight: 300;
	}
	
	.book_available {
		margin-top: 2px;
		font-size: 17px;
	}
	
	.book_description {
		color: gray;
		margin-bottom: 15px;
		margin-left: 4px;
		display: -webkit-box;
		-webkit-line-clamp:3;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}
	
	/* 마지막 수평선은 보이지 않도록 함 */
	.book_split:last-of-type {
		visibility: hidden;
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
				<!-- 정렬 버튼은 검색 중에는 보이지 않도록 함 -->
				<%
				if ("search".equals(menu) == false) {
				%>
				<ul class="orderButton">
					<li><a href="/library/bookList?menu=<%=menu %>&orderby=bookName">가나다순</a>
					<li><a href="/library/bookList?menu=<%=menu %>&orderby=index">최신순</a>
					<li><a href="/library/bookList?menu=<%=menu %>&orderby=count">인기순</a>
				</ul>
				<%
				}
				%>
				
				<!-- 검색 결과가 없다면 -->
				<c:if test="${list.size() == 0}">
					<p style="color: #1A606B; font-size: 20px;">검색 결과가 없습니다.</p>
				</c:if>
				
				<c:forEach var="i" items="${list}">
					<div class="book">
						<a href="/library/bookDetail?bookId=${i.id}"><img class="bookImage" src="/library/${i.image}"></a>
						<div class="book_info">
							<div>
								<p class="book_category">${i.categoryName} 분야</p>
								<p class="book_name"><a href="/library/bookDetail?bookId=${i.id}">${i.name}</a></p>
								<p ><span class="book_writer">지은이 : ${i.writer}</span>
									<span class="split">ㅣ</span>
									<span class="book_publisher">출판사 : ${i.publisher}</span>
								</p>
								<p class="book_available">
									대여 가능 여부 :
									<c:choose>
										<c:when test="${i.isAvailable == true}">
											<span style="color: blue">가능</span>
										</c:when>
										<c:otherwise>
											<span style="color: red">불가능</span>
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<p class="book_description">${i.description}</p>
						</div> <!-- end of book_info -->
					</div>
					<hr class="book_split">
				</c:forEach>

			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>