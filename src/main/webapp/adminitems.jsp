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
<link rel="stylesheet" href="css/adminitems.css">
</head>
<body>

<div class="hgroup">
	<h1>アスリートマーケット(管理者用ページ)</h1>
<!--	追加-->
<!--	<p>こんにちは、${loginUser.name}さん 管理者用<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet?action=mypage">マイページ</a>  <a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>-->
	<p>こんにちは、${loginUser.name}(管理者)さん<br>
	<a href="/team_dev_athletemarket/AdminServlet" method="post">会員一覧</a> 
	<a href="/team_dev_athletemarket/AdminServlet?action=adminItems" method="post">商品一覧</a>
	<a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a>
	</p>
</div>
	
<!--<div class="sub">-->
<!--<form action="/team_dev_athletemarket/ItemServlet" method="get">-->
<!--    <select name="key" onchange="this.form.submit()">-->
<!--        <option value=""  selected disabled>-- 並び替えを選択 --</option>-->
<!--        <option value="low" ${key == 'low' ? 'selected' : ''}>価格の安い順</option>-->
<!--        <option value="high" ${key == 'high' ? 'selected' : ''}>価格の高い順</option>-->
<!--        <option value="new" ${key == 'new' ? 'selected' : ''}>新しい順</option>-->
<!--        <option value="old" ${key == 'old' ? 'selected' : ''}>古い順</option>-->
<!--    </select>-->
<!--    <input type="hidden" name="action" value="adminSort">-->
<!--</form>-->

	
	

	</div>
	
	<c:if test="${status != null}">
		<p class="status">${status}</p>
	</c:if>
	
	<div class="card-wrapper">
		<c:forEach items="${list}" var="item">
			<div class="card">
<!--			追加1-->
			<a href="/team_dev_athletemarket/ItemServlet?action=detail&id=${item.id}">
				<img src="images/${item.imageName}" alt="画像"></a> 
<!--				<img src="images/${item.categoryName}.png" alt="画像"> -->
<!--				<a href="/team_dev_athletemarket/AdminServlet?action=#">商品名：${item.itemName}</a><br>-->
<!--				<a href="#">商品名：${item.itemName}</a><br>-->
				<p>商品名：${item.itemName}</p>
				カテゴリー：${item.categoryName}<br> 出品者名：${item.sellerName}<br>
<!--			変更前-->
<!--			<a href="/team_dev_athletemarket/AdminServlet?action=action&id=${item.id}">商品名：${item.itemName}</a><br>-->
				
				値段：${item.price}円<br>
				メモ：${item.memo}<br>
				出品日：${item.sellDay}<br>

				<div class="card-buttons">
			<form action="/team_dev_athletemarket/AdminServlet" method="post" onsubmit="return beforeSubmit()">
				<input type="hidden" name="action" value="adminItemDelete">
				<input type="hidden" name="id" value="${item.id}">
				 <button class="delete" name="id" value="${item.id}" id="btn" type="submit">削除</button>
			</form>	
			</div>
			
			</div>
		</c:forEach>
	</div>

</body>
		<script>
  function beforeSubmit() {
    if(window.confirm('本当に削除しますか？')) {
    	var result= alert("商品を削除しました");
      		return result;
    	} else {
      		return false;
    	}
  	}
	</script>



</html>