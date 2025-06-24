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
<link rel="stylesheet" href="css/mypage.css">
</head>
<body>

	<h1 style="background-color:blue;">スポーツ用品CtoC売買サイト</h1>
	<p>こんにちは、${loginUser.name}さん<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet">全商品一覧</a> <a href="/team_dev_athletemarket/ItemServlet?action=history">購入履歴</a>  <a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>
	<p>
	<h1>マイページ</h1>
	
	<form action="/team_dev_athletemarket/ItemServlet" method="get">
    <select name="key" onchange="this.form.submit()">
        <option value=""  selected disabled>-- 並び替えを選択 --</option>
        <option value="low" ${key == 'low' ? 'selected' : ''}>価格の安い順</option>
        <option value="high" ${key == 'high' ? 'selected' : ''}>価格の高い順</option>
        <option value="new" ${key == 'new' ? 'selected' : ''}>新しい順</option>
        <option value="old" ${key == 'old' ? 'selected' : ''}>古い順</option>
    </select>
    <input type="hidden" name="action" value="mypageSort">
	</form>

	<div class="btns">
		<form action="/team_dev_athletemarket/ItemServlet" method="get">
			<button>出品</button>
			<input type="hidden" name="action" value="listing">
		</form>
		</div>
	</p>
	<c:if test="${status != null}">
		<p class="status">${status}</p>
	</c:if>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">
		
			<div class="card">
			<c:choose>
				<%-- 購入されていない --%>
				<c:when test="${empty item.purchaseDay}">
					<img src="images/${item.imageName}" alt="画像"><br>
				</c:when>
				<%-- 購入されている --%>
				<c:otherwise>
					<img src="images/soldout.png" alt="画像"><br>
				</c:otherwise>
			</c:choose>
				 <a href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">商品名：${item.itemName}</a><br>
				カテゴリー：${item.categoryName}<br> 
				出品者名：${item.sellerName}<br>
    			値段：<fmt:formatNumber value="${item.price}" type="number"/>円<br>
    			<c:choose>
				<%-- 購入されていない --%>
				<c:when test="${empty item.purchaseDay}">
			    <form action="/team_dev_athletemarket/ItemServlet?action=edit" method="post">
        		<button name="id" value="${item.id}">変更</button>
				</form>
				<form action="/team_dev_athletemarket/ItemServlet?action=itemDelete" method="post" onsubmit="return beforeSubmit()">
        		<button name="id" value="${item.id}" id="btn" type="submit">削除</button>
				</form>
				</c:when>
				<%-- 購入されている --%>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
    			
			</div>

		</c:forEach>
	</div>
	
	<a href="/team_dev_athletemarket/ItemServlet">商品一覧に戻る</a>
	
		<script>
  function beforeSubmit() {
    if(window.confirm('本当に削除しますか？')) {
      return true;
    	} else {
      	return false;
    	}
  	}
	</script>
</body>
</html>