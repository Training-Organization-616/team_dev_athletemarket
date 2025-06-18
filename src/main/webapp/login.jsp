<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>スポーツ用品CtoC売買サイト</h1>
<p>${message}</p>
<form action="/team_dev_athletemarket/LoginServlet" method="post">
メールアドレス：<input type="text" name="email"><br>
パスワード：<input type="password" name="password"><br>
<input type="hidden" name="action" value="login">
<button>ログイン</button>
</form>

<!--新規会員登録へ-->
<p><a href = "/team_dev_athletemarket/registuser.jsp">新規登録(リンク)</a></p>

</body>
</html>