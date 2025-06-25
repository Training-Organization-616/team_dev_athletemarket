<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="./css/login.css">
</head>
<body>
<div class="login">
    <div class="login-screen">
      <div class="app-title">
		<h1>アスリートマーケット</h1>
	  <div>
	<form action="/team_dev_athletemarket/UserServlet?action=add" method="post">
	<div class="login-form">

	<c:if test="${not empty message}">
		<div><font color="red"><small>${message}</small></font></div>
		<!--エラーメッセージを出力-->
	</c:if>

        <div class="control-group">
     	   <input type="text" class="login-field" value="${name}" placeholder="username" id="login-name" name="name" required>
      	   <label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="text" class="login-field" value="${address}" placeholder="address" id="login-name" name="address" required>
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="text" class="login-field" value="${tel}" placeholder="phone number" id="login-name" name="tel" required>
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

		birthday
		<div class="control-group">
		<input type="DATE" id="birth_day" name="birth_day" placeholder="生年月日" value="${birth_day == null ? '2000-01-01' : birth_day}">
		</div>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const today = new Date().toISOString().split("T")[0];
    document.getElementById("birth_day").setAttribute("max", today);
  });
</script>
        <div class="control-group">
        	<input type="email" class="login-field" value="${email}" placeholder="email" id="login-name" name="email" required>
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="password" class="login-field" value="${password}" placeholder="password" id="login-pass" name="password" required>
        	<label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
        
        <div class="control-group">
        	<input type="password" class="login-field" value="${passwordCheck}" placeholder="repeat password" id="login-pass" name="passwordCheck" required>
        	<label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>

		<button class="btn btn-primary btn-large btn-block">Register</button>
</form>

<a class="login-link" href = "/team_dev_athletemarket/LoginServlet">Login</a>
      </div>
    </div>
  </div>
</body>
</html>