<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav>
	<h1><span style="color:#1A606B;">마이페이지</span></h1>
	<table class="category_table" border="1">
		<tr>
			<td><a href="/library/myPage?menu=userInfo" class="myPage_userInfo" style="font-size: 17px;">회원 정보 수정</a></td>
		</tr>
		<tr>
			<td><a href="/library/myPage?menu=myBooks" class="myPage_myBooks" style="font-size: 17px;">내 서재</a></td>
		</tr>
		<tr>
			<td style="border-bottom: hidden;"><a href="/library/myPage?menu=manageReview" class="myPage_manageReview" style="font-size: 17px;">리뷰 관리</a></td>
		</tr>
	</table>
</nav>