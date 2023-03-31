<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav>
	<h1><span style="color:#1A606B;">카테고리</span></h1>
	<table class="category_table" border="1">
		<tr>
			<td><a href="/library/bookList?menu=all" class="cate_all">전체</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=it" class="cate_it">IT</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=novel" class="cate_novel">소설</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=hobby" class="cate_hobby">취미</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=economy" class="cate_economy">경제</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=improvement" class="cate_improvement">자기계발</a></td>
		</tr>
		<tr>
			<td><a href="/library/bookList?menu=cook" class="cate_cook">요리</a></td>
		</tr>
		<tr class="table_last">
			<td><a href="/library/bookList?menu=child" class="cate_child">어린이</a></td>
		</tr>
	</table>
</nav>