<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.young.lib.service.UserService"%>
<%@page import="com.young.lib.dto.UserDTO"%>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// 최초로 접근했을 때는 null이라서 아래 코드 실행 X
	// 로그인 버튼을 누르고 나면 null이 아니라서 아래 코드 실행 O
	if (id != null && password != null) {
		UserService userService = new UserService();
		
		// null : 실패
		UserDTO loginResult = userService.login(id, password);
		// null이 아니라면 로그인 성공
		if (loginResult != null) {
			session.setAttribute("id", id);
			out.println("<script> alert('환영합니다!'); location.href='/library/index.jsp';</script>");
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
<link rel = "stylesheet" href = "../css/form.css">
<style type="text/css">
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
						<input name="id" id="id" type="text" required="required" style="padding:0 3px">					
					</div>
					<div class="form-group">
						<label for="pw">비밀번호</label>
						<input name="password" id="pw" type="password" required="required" style="padding:0 3px">					
					</div>
					<div class="form-btn">
						<input class="btn"  name="login"  type="submit" value="완료">
					</div>
				</form>
			</section>
		</div>
		<jsp:include page="../layout/footer.jsp" />
	</div> <!-- end of page-container -->
	
</body>
</html>