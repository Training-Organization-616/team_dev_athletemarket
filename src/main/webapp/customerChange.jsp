<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更画面</title>
</head>
<body>

<h1>スポーツ用品CtoC売買サイト</h1>

<form action="/team_dev_athletemarket/UserServlet?action=updateCheck" method="post">

<c:if test="${not empty message}">
<p>${message}</p>
<!--エラーメッセージを出力-->
</c:if>

<input type="text" name="name" placeholder="名前">
<br>

<input type="text" name="address" placeholder="住所">
<br>

<input type="text" name="tel" placeholder="電話番号">
<br>

<input type="DATE" name="birth_day" placeholder="生年月日">
<br>

<input type="email" name="email" placeholder="メールアドレス">
<br>

<input type="password" name="password" placeholder="パスワード">
<br>

<input type="password" name="passwordCheck" placeholder="確認パスワード">
<br>

<button>確定</button>

</form>

</body>
</html>