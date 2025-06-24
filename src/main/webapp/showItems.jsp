<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--カンマ表示用ライブラリ-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
<link rel="stylesheet" href="css/showItems.css">
</head>
<body>

<div class="hgroup">
	<h1>アスリートマーケット</h1>
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
</div>
	
<div class="sub">
<form action="/team_dev_athletemarket/ItemServlet" method="get">
    <select name="key" onchange="this.form.submit()">
        <option value=""  selected disabled>-- 並び替えを選択 --</option>
        <option value="low" ${key == 'low' ? 'selected' : ''}>価格の安い順</option>
        <option value="high" ${key == 'high' ? 'selected' : ''}>価格の高い順</option>
        <option value="new" ${key == 'new' ? 'selected' : ''}>新しい順</option>
        <option value="old" ${key == 'old' ? 'selected' : ''}>古い順</option>
    </select>
    <input type="hidden" name="action" value="sort">
</form>

	
	
	<p>
		<form action="/team_dev_athletemarket/ItemServlet" method="get">
			<button>出品</button>
			<input type="hidden" name="action" value="listing">
		</form>
	</p>
	</div>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">

			<div class="card">
				<img src="images/${item.imageName}" alt="画像"> <a
					href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">商品名：${item.itemName}</a><br>
				カテゴリー：${item.categoryName}<br> 出品者名：${item.sellerName}<br>
    			値段：<fmt:formatNumber value="${item.price}" type="number"/>円<br>
			</div>

		</c:forEach>
	</div>

</body>
</html>