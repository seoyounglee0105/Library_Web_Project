<%@page import="com.young.lib.controller.UserController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	    String id = (String) session.getAttribute("id");
    	UserController userController = new UserController();
    	String name = userController.requestUserInfo(id).getName();
    %>
    
		<div class="top-container">
			<header class="header_login">
				<h4 class="li_name"><%=name %> 님</h4>
				<ul class="right_ul">
					<li><a href="/library/intro/logout.jsp">로그아웃</a>
					<li><a href="#">마이페이지</a>
				</ul>
			</header>

			<div class="home_search">
				<a href="/library/index.jsp"><img alt="홈 버튼" src="/library/images/homebtn.png"></a>
				<!-- 검색 기능 아직 없음 -->
				<form class="search" action="/library/bookList?menu=search"  method="post">
					<input name = "searchBook" type="text" placeholder="검색어를 입력하세요.">
					<button type="submit"><img src="/library/images/search.png"></button>
				</form>
			</div>
		</div>
		<!-- end of top-container -->