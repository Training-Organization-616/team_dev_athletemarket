<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--カンマ表示用ライブラリ-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" href="css/detailItem.css">
</head>
<body>
 
	<h1 style="text-align: center;">${bean.itemName}</h1>
 
	<div class="categoryImage">
		<img src="images/${bean.imageName}">
	</div>
 
	<div class="itemInfo">
		<div class="Form">
			<div class="Form-Item">
				<p class="Form-Item-Label">カテゴリー：${bean.categoryName}</p>
			</div>
 
			<div class="Form-Item">
				<p class="Form-Item-Label">出品者名：${bean.sellerName}</p>
			</div>
 
			<div class="Form-Item">
				<p class="Form-Item-Label">値段：<fmt:formatNumber value="${bean.price}" type="number"/>円</p>
			</div>
 
			<div class="Form-Item">
				<p class="Form-Item-Label">メモ：${bean.memo}</p>
			</div>
 
			<div class="Form-Item">
				<p class="Form-Item-Label">出品日：${bean.sellDay}</p>
			</div>
 
			<c:choose>
				<%-- 購入されていない --%>
				<c:when
					test="${empty bean.purchaseDay && loginUser.id != bean.sellerId}">
					<form action="/team_dev_athletemarket/ItemServlet" method="post"
						onsubmit="return check()">
						<input type="hidden" name="action" value="purchase"> <input
							type="hidden" name="id" value="${bean.id}">
						<button class="purchase">購入</button>
					</form>
				</c:when>
				<%-- 購入されていない、かつ出品したユーザー --%>
				<c:when
					test="${empty bean.purchaseDay && loginUser.id == bean.sellerId}">
				<div class="Btns">
					<form action="/team_dev_athletemarket/ItemServlet?action=edit"
						method="post">
						<button name="id" value="${bean.id}" class="update">変更</button>
					</form>
					<form
						action="/team_dev_athletemarket/ItemServlet?action=itemDelete"
						method="post" onsubmit="return beforeSubmit()">
						<button name="id" value="${bean.id}" id="btn" type="submit" class="delete">削除</button>
					</form>
				</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<form action="/team_dev_athletemarket/ItemServlet" method="get">
		<button class="Form-BtnBack">戻る</button>
	</form>
 
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