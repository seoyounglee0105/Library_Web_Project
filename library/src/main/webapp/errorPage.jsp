<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자 도서관</title>
<style type="text/css">
	body {
		display: flex;
		flex-direction: column;
	}
	.home {
		width: 350px;
		height: 100px;
		margin: 10px;
	}
	
	.error {
		margin-top: 100px;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.error_img {
		width: 130px;
		height: 130px;
	}
	
	.error p {
		text-align: center;
	}
</style>
</head>
<body>
	<a href="/library"><img alt="홈 버튼" src="/library/images/homebtn.png" class="home"></a>
	<div class="error">
		<img alt="error" src="/library/images/error.png" class="error_img">
		<p style="font-size: 20px; font-weight: 400">죄송합니다.<br>요청하신 페이지를 찾을 수 없습니다.</p>
		<p style="color: gray;">	
			방문하시려는 페이지의 주소가 잘못 입력되었거나, <br>
			페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다. <br>
		</p>
		<p style="color: gray;"> 입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다. </p>
	</div>
</body>
</html>