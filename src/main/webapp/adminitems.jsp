<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者商品一覧画面</title>
<link rel="stylesheet" href="css/showItems.css">
</head>
<body>

	<h1 style="background-color:blue;">スポーツ用品CtoC売買サイト</h1>
<!--	追加-->
<!--	<p>こんにちは、${loginUser.name}さん 管理者用<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet?action=mypage">マイページ</a>  <a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>-->
	<p>こんにちは、${loginUser.name}さん 管理者用<br><a href="/team_dev_athletemarket/AdminServlet" method="post">会員一覧</a> <a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>
		
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

			<form action="/team_dev_athletemarket/AdminServlet" method="post" onsubmit="return beforeSubmit()">
				<input type="hidden" name="action" value="adminItemDelete">
				<input type="hidden" name="id" value="${item.id}">
				 <button class="delete" name="id" value="${item.id}" id="btn" type="submit">削除</button>
			</form>	
			</div>
		</c:forEach>
	</div>
	
	
<!--	itemDetailから追加する。-->
<!--	<h3>${bean.itemName}</h3>-->

<!--	<img src="images/${bean.categoryName}.png">-->
<!--	<br> <a href = "/team_dev_athletemarket/itemDetail.jsp">商品名:</a>-->
<!--	<br> カテゴリー：${bean.categoryName}-->
<!--	<br>-->
<!--	<br> 出品者名：${bean.customerName}-->
<!--	<br>-->
<!--	<br> 値段：${bean.price}円-->
<!--	<br>-->
<!--	<br> メモ：${bean.memo}-->
<!--	<br>-->
<!--	<br>-->

<!--<form action="/team_dev_athletemarket/UserServlet" method="get">-->
<!--  <button>削除</button>-->
<!--  <input type="hidden" name="action" value="withdrawal">-->
<!--</form>-->
<!--	itemDetailから追加した。-->
<!--	adminから追加した。-->
<!--削除ダイアログ-->
<!--		追加　コメント	-->
	<c:if test="${status != null}">
		<p class="status">${status}</p>
	</c:if>


<script>
  function beforeSubmit() {
    if(window.confirm('この商品を削除しますか？')) {
      return true;
    } else {
      return false;
    }
  }
</script>
<!--	adminから追加した。-->
</body>
</html>