<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報確認ページ</title>
<link rel="stylesheet" href="css/confirm.css">

</head>
<body>
<h1 style="text-align : center;">登録内容確認</h1>

	<div class="Form">

	<form action="/team_dev_athletemarket/UserServlet?action=addUser" method="post">

	<div class="Form-Item">
		<p class="Form-Item-Label">名前: ${name}</p>
		<input type="hidden" name="name" value="${name}">
	</div>

	<div class="Form-Item">
		<p class="Form-Item-Label">住所: ${address}</p>
		<input type="hidden" name="address" value="${address}">
	</div>

	<div class="Form-Item">
		<p class="Form-Item-Label">電話番号: ${tel}</p>
		<input type="hidden" name="tel" value="${tel}">
	</div>
	
	<div class="Form-Item">
		<p class="Form-Item-Label">生年月日: ${birth_day}</p>
		<input type="hidden" name="birth_day" value="${birth_day}">
	</div>

	<div class="Form-Item">
		<p class="Form-Item-Label">メールアドレス: ${email}</p>
		<input type="hidden" name="email" value="${email}">
	</div>
	
	<div class="Form-Item">
		<p class="Form-Item-Label">パスワード: ${password}</p>
		<input type="hidden" name="password" value="${password}">
	</div>

	<div class="Form-Item">
	<p class="Form-Item-Label" style="color:#ff6666">以上の内容で登録します。よろしいですか？</p>
	</div>

<button onclick="clickEvent()" class="Form-Btn">確定</button>

</form>

<form action="/team_dev_athletemarket/UserServlet" method="get">

<button class="Form-BtnBack">戻る</button>

</form>

<script>
	function clickEvent() {
    	alert('新規登録が完了しました');
	}
</script>

</body>
</html>