<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	.writeReview {
		margin: 0 30px;
	}
	
	.form-row {
		display: flex;
		align-items: flex-end;
		justify-content: space-between;	
		margin-bottom: 10px;	
	}
	
	.writeReview select {
		font-size: 17px;
		width: 300px;
	}
	
	.title {
		font-size: 20px;
		width: 676px;
	}
	
	.content {
		font-size: 17px;
		padding: 5px;
		resize: none;
	}
	
	.star {
		display: flex;
		flex-direction: column;
		width: 155px;
		margin-right: 2px;
	}
	
	.form-btn {
		display: flex;
		justify-content: center;
		margin-top: 30px;
	}
	
	.btn {
		font-size: 15px;
		line-height: 0;
		border-radius: 8px;
		border-width: thin;
		height: 28px;
		width: 50px;
		text-align: center;
		background-color: #B0DFD3;
	}

	.btn:hover {
		background-color: #80BFB5;
		cursor: pointer;
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
				
			<section style="flex-basis: 800px;">
				<h3>리뷰 작성</h3>
				<!-- ${bookList}로 리뷰 작성 가능한 책 리스트 가져올 수 있음 -->
				<form action="/library/myPage?action=writeReview" method="post" class="writeReview" enctype="multipart/form-data">
					<div class="form-row" style="margin-bottom: 20px;">
						<select name="bookId">
							<option value="-1">도서를 선택해주세요.</option>
							<c:forEach var="i" items="${bookList}">
								<option value="${i.id}">${i.name}</option>
							</c:forEach>
						</select>
						<div style="display:flex; align-items: flex-end;">
							<label style="margin-right: 10px;">평점</label>
							<div class="star">
								<pre style="color:gray;">1      2      3      4      5</pre>
								<input type="range" name="star" min="1" max="5" value="3">
							</div>
						</div>
					</div>
					<div class="form-row">
						<input type="text" name="title" placeholder="제목을 입력해주세요." maxlength="100" required="required" class="title">
					</div>
					<div class="form-row">
						<textarea name="content" cols="72" rows="10" placeholder="내용을 입력해주세요." required="required" class="content" ></textarea>
					</div>
					<div class="form-row">
						<input type="file" class="form_file" id="file" name="file">
					</div>
					<div class="form-btn">
						<button class="btn" type="submit" onclick="return confirm('작성을 완료하셨습니까?')">완료</button>
					</div>
				</form>
				
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div>
	<!-- end of page-container -->

</body>
</html>