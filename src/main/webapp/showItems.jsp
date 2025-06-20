<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
<link rel="stylesheet" href="css/showItems.css">
</head>
<body>

	<h1 style="background-color:blue;">スポーツ用品CtoC売買サイト</h1> 
	<c:choose>
		<c:when test="${loginUser != null}">
			<p>こんにちは、${loginUser.name}さん<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet?action=mypage">マイページ</a>
			<a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>
		</c:when>
		<c:otherwise>
			<p>こんにちは、ゲストさん<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet?action=mypage">マイページ</a>
			<a href="/team_dev_athletemarket/LoginServlet?action=">ログイン</a></p>
		</c:otherwise>
	</c:choose> 
	
	<p>
		<form action="/team_dev_athletemarket/ItemServlet" method="get">
			<button>出品</button>
			<input type="hidden" name="action" value="listing">
		</form>
	</p>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">

			<div class="card">
				<img src="images/${item.categoryName}.png" alt="画像"> <a
					href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">商品名：${item.itemName}</a><br>
				カテゴリー：${item.categoryName}<br> 出品者名：${item.customerName}<br>
				値段：${item.price}円<br>
			</div>

		</c:forEach>
	</div>

</body>
</html>