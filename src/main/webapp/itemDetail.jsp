<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
	<h1 style="background-color: blue;">スポーツ用品CtoC売買サイト</h1>

	<h3>${bean.itemName}</h3>

	<img src="images/${bean.imageName}">
	<br> カテゴリー：${bean.categoryName}
	<br>
	<br> 出品者名：${bean.sellerName}
	<br>
	<br> 値段：${bean.price}円
	<br>
	<br> メモ：${bean.memo}
	<br>
	<br>

	<c:choose>
		<%-- 購入されていない --%>
		<c:when test="${empty bean.purchaseDay && loginUser.id != bean.sellerId}">
			<form action="/team_dev_athletemarket/ItemServlet" method="post" onsubmit="return check()">
				<input type="hidden" name="action" value="purchase">
				<input type="hidden" name="id" value="${bean.id}">
				<button>購入</button>
			</form>	
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>

	<p><a href="/team_dev_athletemarket/ItemServlet">商品一覧画面へ戻る</a></p>

</body>

<script>
	function check() {
		if (${empty loginUser}){
			var result= alert("ログイン必須です");
			return result;
		}else{
		var result = window.confirm("カテゴリー：${bean.categoryName}\n\n出品者名：${bean.sellerName}\n\n値段：${bean.price}円\n\nメモ(任意)：${bean.memo}\n\n" + "                           " + "以上の内容で購入します。よろしいですか？");
		return result;
		}
	}
</script>
</html>