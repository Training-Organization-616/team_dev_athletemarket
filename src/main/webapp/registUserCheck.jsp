<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報確認ページ</title>
</head>
<body>

<h1>スポーツ用品CtoC売買サイト</h1>

<form action="/team_dev_athletemarket/UserServlet?action=addUser" method="post">

<p>名前: ${name}</p>
<input type="hidden" name="name" value="${name}">

<p>住所: ${address}</p>
<input type="hidden" name="address" value="${address}">

<p>電話番号: ${tel}</p>
<input type="hidden" name="tel" value="${tel}">

<p>生年月日: ${birth_day}</p>
<input type="hidden" name="birth_day" value="${birth_day}">

<p>メールアドレス: ${email}</p>
<input type="hidden" name="email" value="${email}">

<p>パスワード: ${password}</p>
<input type="hidden" name="password" value="${password}">

<p>以上の内容で登録します。よろしいですか？</p>

<button>確定</button>

</form>

<form action="/team_dev_athletemarket/UserServlet" method="get">

<button>戻る</button>

</form>

</body>
</html>