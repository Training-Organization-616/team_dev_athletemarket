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
	<p>こんにちは、後で値を入れるさん<br><a href="#">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet/?action=mypage">マイページ</a>  <a href="#">ログアウト</a></p>
	<p>
		<form action="/team_dev_athletemarket/ItemServlet" method="get">
			<button>出品</button>
			<input type="hidden" name="action" value="listing">
		</form>
		<form action="/team_dev_athletemarket/UserServlet" method="get">
			<button>退会</button>
			<input type="hidden" name="action" value="widthdrawlUser">
		</form>
	</p>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">

			<div class="card">
				<img src="images/sample.png" alt="画像"><br> <a
					href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">商品名：${item.itemName}</a><br>
				カテゴリー：${item.categoryName}<br> 出品者名：${item.customerName}<br>
				値段：${item.price}円<br>
			</div>

		</c:forEach>
	</div>

</body>
</html>