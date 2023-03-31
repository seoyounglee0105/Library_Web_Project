<%@page import="com.young.lib.controller.UserController"%>
<%@page import="com.young.lib.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<%

request.setCharacterEncoding("UTF-8");
String action = request.getParameter("action");
if ("signUp".equals(action)) {
	UserController userController = new UserController();

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("userName");
	String phoneNumber = request.getParameter("phoneNumber");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	UserDTO userDTO = new UserDTO(id, password, name, phoneNumber, address, email);
	
	int responseType = userController.requestSignUp(userDTO);
	// 0 : 실패, 1 : 성공, 2 : 아이디 중복, 3 : 전화번호 중복, 4 : 전화번호 형식 틀림
	if (responseType == 1) { %>
		<script>
			alert('회원가입에 성공했습니다.');
			location.href = '/library/index.jsp';
		</script>
		
	<% } else if (responseType == 2) { %>
		<script>
			alert('중복된 아이디입니다.');
			history.back();
		</script>
		
	<% } else if (responseType == 3) { %>
		<script>
			alert('중복된 전화번호입니다.');
			history.back();
		</script>
		
	<% } else if (responseType == 4) { %>
		<script>
			alert('전화번호의 형식이 틀렸습니다.\n###-####-#### 형식으로 작성해주세요.');
			history.back();
		</script>
		
	<% } else { %>
		<script>
			alert('회원가입에 실패했습니다.');
			history.back();
		</script>
	<% } %>
	
<% } %>
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
				<h1 class="title">회원 가입</h1>
			
				<form class="form-intro" action="/library/intro/signUp.jsp?action=signUp" method="post">
					<div class="form-group">
						<label for="id">아이디 *</label>
						<input name="id" id="id" type="text" required="required" >					
					</div>
					<div class="form-group">
						<label for="pw">비밀번호 *</label>
						<input name="password" id="pw" type="password" required="required">					
					</div>
					<div class="form-group">
						<label for="userName">이름 *</label>
						<input name="userName" id="userName" type="text" required="required">					
					</div>
					<div class="form-group">
						<label for="phoneNumber">전화번호 *</label>
						<input name="phoneNumber" id="phoneNumber" type="tel" required="required" placeholder="###-####-####">					
					</div>
					<div class="form-group">
						<label for="address">주소 *</label>
						<input name="address"  id="address" type="text" required="required">					
					</div>
					<div class="form-group">
						<label for="email">이메일</label>
						<input name="email"  id="email" type="email">					
					</div>
					<div class="form-btn">
						<input class="btn"  name="signUp"  type="submit" value="완료">
					</div>
				</form>
			</section>
		</div>
	</div> <!-- end of page-container -->
	
</body>
</html>