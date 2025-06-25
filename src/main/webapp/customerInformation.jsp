<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報画面</title>
<link rel="stylesheet" href="css/confirm.css">
</head>
<body>

	<h1 style="text-align : center;">会員情報変更</h1>

	<div class="Form">

		<form action="/team_dev_athletemarket/UserServlet?action=updatePage"
			method="post">

			<div class="Form-Item">
				<p class="Form-Item-Label">名前: ${loginUser.name}</p>
			</div>

			<div class="Form-Item">
				<p class="Form-Item-Label">住所: ${loginUser.address}</p>
			</div>

			<div class="Form-Item">
				<p class="Form-Item-Label">電話番号: ${loginUser.tel}</p>
			</div>
			<div class="Form-Item">
				<p class="Form-Item-Label">生年月日: ${loginUser.birthDay}</p>
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label">メールアドレス: ${loginUser.email}</p>
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label">入会年月： ${loginUser.startDay}</p>
			</div>
			
			<button class="Form-Btn">変更</button>

		</form>

	</div>

	<form action="/team_dev_athletemarket/UserServlet" method="get">
		<button class="Form-BtnOut">退会</button>
		<input type="hidden" name="action" value="withdrawal">
	</form>

	<form action="/team_dev_athletemarket/ItemServlet" method="get">

		<button class="Form-BtnBack">戻る</button>

	</form>
</body>
</html>