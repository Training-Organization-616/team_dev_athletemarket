<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報画面</title>
</head>
<body>

<h1>スポーツ用品CtoC売買サイト</h1>

<form action="/team_dev_athletemarket/UserServlet?action=updatePage" method="post">

<p>名前: ${name}</p>

<p>住所: ${address}</p>

<p>電話番号: ${tel}</p>

<p>生年月日: ${birth_day}</p>

<p>メールアドレス: ${email}</p>

<p>入会年月： ${start_day}</p>

<button>変更</button>

</form>

<form action="/team_dev_athletemarket/ItemServlet" method="get">

<button>戻る</button>

</form>

</body>
</html>