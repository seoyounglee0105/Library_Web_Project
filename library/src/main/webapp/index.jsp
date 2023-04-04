<%@page import="java.util.Random"%>
<%@page import="com.young.lib.dto.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.young.lib.service.BookService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	// 세션 확인하기
	String id = (String) session.getAttribute("id");

	// 추천 책 정보 가져오기
	BookService bookService = new BookService();
	ArrayList<BookDTO> topBooks = bookService.viewTopBook(15);
	
	// 가져올 책들을 랜덤으로 정함
        int[] numArr = new int[4];

        Random r = new Random();  
        for (int i = 0; i < numArr.length; i++) {
            // 1 ~ 10 범위의 정수 난수를 생성해서 배열에 넣기
            numArr[i] = r.nextInt(15);
            // 중복 제거
            for (int j = 0; j < i; j++) {
                if (numArr[i] == numArr[j]) {
                    i--; // 이번 반복을 취소하고 다시 하도록 함
                }
            }
        } // end of for
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/category.css">
<style>

	section h1 {
		margin-top: 15px;
		margin-bottom: 10px;
		color: #1A606B; 
	}
	
	.book_list {
		display: flex;
		margin: 30px;
		justify-content: space-between;
	}
	
	.book {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.book_image {
		width: 180px;
		height: 240px;
		border: 1px solid gray;
		margin-bottom: 10px;
	}
	
	.book_name {
		text-align: center;
		font-size:14px;
	}
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
			
			<section>
			
				<h1>추천 도서</h1>
				<hr style="color: #1A606B; ">
				<div class="book_list">
				<% for (int i = 0; i < numArr.length; i++) { %>
					<div class="book">
						<a href="/library/bookDetail?bookId=<%=topBooks.get(numArr[i]).getId()%>">
							<img class="book_image" src="/library/<%=topBooks.get(numArr[i]).getImage()%>">
						</a>
						<div class="book_name">
							<a href="/library/bookDetail?bookId=<%=topBooks.get(numArr[i]).getId()%>">
								<%=topBooks.get(numArr[i]).getName()%>
							</a>
						</div>
					</div>
				<% } %>
				</div>
				
			
			</section>
		</div>
		<jsp:include page="layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>