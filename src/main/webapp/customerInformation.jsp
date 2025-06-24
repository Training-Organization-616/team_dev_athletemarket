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

<p>名前: ${loginUser.name}</p>

<p>住所: ${loginUser.address}</p>

<p>電話番号: ${loginUser.tel}</p>

<p>生年月日: ${loginUser.birthDay}</p>

<p>メールアドレス: ${loginUser.email}</p>

<p>入会年月： ${loginUser.startDay}</p>

<button>変更</button>

</form>

		<form action="/team_dev_athletemarket/UserServlet" method="get">
			<button>退会</button>
			<input type="hidden" name="action" value="withdrawal">
		</form>

<form action="/team_dev_athletemarket/ItemServlet" method="get">

<button>戻る</button>

</form>

</body>
</html>