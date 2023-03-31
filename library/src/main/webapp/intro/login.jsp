<%@page import="com.young.lib.dto.UserDTO"%>
<%@page import="com.young.lib.controller.UserController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// 최초로 접근했을 때는 null이라서 아래 코드 실행 X
	// 로그인 버튼을 누르고 나면 null이 아니라서 아래 코드 실행 O
	if (id != null && password != null) {
		UserController userController = new UserController();
		
		// null : 실패
		UserDTO loginResult = userController.requestLogin(id, password);
		// null이 아니라면 로그인 성공
		if (loginResult != null) {
			session.setAttribute("id", id);
			response.sendRedirect("../index.jsp");
		} else {  // 로그인 실패 시 경고 창을 띄우고 다시 login.jsp로 이동
			out.println("<script> alert('로그인에 실패했습니다.'); history.back(); </script>");
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<link rel = "stylesheet" href = "../css/main.css">
<link rel = "stylesheet" href = "../css/intro.css">
<style type="text/css">
	.title {
		font-family: 'Noto Sans KR', sans-serif;
		margin: 8px;
		font-size: 30px;
	}
	
	.form-intro {
		padding: 20px;
		width: 300px;
	}
	
	.btn {
		font-size: 17px;
		border: hidden;
		line-height: 0;
		border-radius: 8px;
		height: 27px;
		width: 50px;
		text-align: center;
		background-color: #95D7C7;
	}
	
	
	.form-group input {
		margin-bottom: 10px;
		height: 30px;
		border-radius: 8px;
		padding: 0 5px;
		font-size: 18px;
		line-height: 0;
		border: 2px solid #4D504F;
	}

</style>
</head>
<body>
	<div class="page-container">
		<!-- 상단 메뉴 -->
		<jsp:include page="../layout/header.jsp"/>
		
	 	<div class="main-container">
			<section>
				<h1 class="title">로그인</h1>
				
				<form class="form-intro" action="/library/intro/login.jsp?action=login" method="post">
					<div class="form-group">
						<label for="id">아이디</label>
						<input name="id" id="id" type="text" required="required" >					
					</div>
					<div class="form-group">
						<label for="pw">비밀번호</label>
						<input name="password" id="pw" type="password" required="required">					
					</div>
					<div class="form-btn">
						<input class="btn"  name="login"  type="submit" value="완료">
					</div>
				</form>
			</section>
		</div>
	</div> <!-- end of page-container -->
	
</body>
</html>