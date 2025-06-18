<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
<link rel="stylesheet" href="css/mypage.css">
</head>
<body>

	<h1 style="background-color:blue;">スポーツ用品CtoC売買サイト</h1>
	<p>こんにちは、後で値を入れるさん<br><a href="#">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet">全商品一覧</a>  <a href="#">ログアウト</a></p>
	<p>
	<div class="btns">
		<form action="/team_dev_athletemarket/ItemServlet" method="get">
			<button>出品</button>
			<input type="hidden" name="action" value="listing">
		</form>
		<form action="/team_dev_athletemarket/UserServlet" method="get">
			<button>退会</button>
			<input type="hidden" name="action" value="widthdrawlUser">
		</form>
		</div>
	</p>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">

			<div class="card">
			<c:choose>
				<%-- 購入されていない --%>
				<c:when test="${empty item.purchaseDay}">
					<img src="images/sample.png" alt="画像"><br>
				</c:when>
				<%-- 購入されている --%>
				<c:otherwise>
					<img src="images/soldout.png" alt="画像"><br>
				</c:otherwise>
			</c:choose>
				 <a href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">商品名：${item.itemName}</a><br>
				カテゴリー：${item.categoryName}<br> 
				出品者名：${item.customerName}<br>
				値段：${item.price}円<br>
			</div>

		</c:forEach>
	</div>
	
	<p><a href="/team_dev_athletemarket/ItemServlet">商品一覧に戻る</a></p>

</body>
</html>