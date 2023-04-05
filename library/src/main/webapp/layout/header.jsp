<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		<div class="top-container">
			<header class="header_logout">
				<div>
					<a href="/library" class="li_name">홈으로</a>
				</div>
				<ul class="right_ul">
					<li><a href="/library/intro?menu=login">로그인</a>
					<li><a href="/library/intro?menu=signUp">회원가입</a>
				</ul>
			</header>

			<div class="home_search">
				<a href="/library"><img alt="홈 버튼" src="/library/images/homebtn.png"></a>
				<!-- 검색 기능 아직 없음 -->
				<form class="search" action="/library/bookList?menu=search"  method="post">
					<input name = "searchBook" type="text" placeholder="검색어를 입력하세요." required="required">
					<button type="submit"><img src="/library/images/search.png"></button>
				</form>
			</div>
		</div>
		<!-- end of top-container -->