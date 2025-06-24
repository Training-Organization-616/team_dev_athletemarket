<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/login.css">
<title>ログイン</title>
</head>
<body>
<div class="login">
    <div class="login-screen">
    	<div class="app-title">
			<h1>アスリートマーケット</h1>
		</div>
		<div class="login-form">
		<c:if test="${message != null}">
		<div class="control-group">
			<font color="red"><small>${message}</small></font>
		</div>
		</c:if>
		<form action="/team_dev_athletemarket/LoginServlet?action=login" method="post">
        <div class="control-group">
        	<input type="text" class="login-field" value="" placeholder="email" id="login-name" name="email" required>
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="password" class="login-field" value="" placeholder="password" id="login-pass" name="password" required>
       		<label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
        <button class="btn btn-primary btn-large btn-block">Login</button>
</form>

<!--新規会員登録へ-->
		<a class="login-link" href = "/team_dev_athletemarket/UserServlet">Create an account</a>
      </div>
    </div>
  </div>
</body>
</html>