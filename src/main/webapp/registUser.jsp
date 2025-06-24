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
     	   <input type="text" class="login-field" value="" placeholder="username" id="login-name" name="name">
      	   <label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="text" class="login-field" value="" placeholder="address" id="login-name" name="address">
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="text" class="login-field" value="" placeholder="phone number" id="login-name" name="tel">
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

		birthday
		<div class="control-group">
		<input type="DATE" id="birth_day" name="birth_day" placeholder="生年月日" value="2000-01-01">
		</div>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const today = new Date().toISOString().split("T")[0];
    document.getElementById("birth_day").setAttribute("max", today);
  });
</script>
        <div class="control-group">
        	<input type="text" class="login-field" value="" placeholder="email" id="login-name" name="email">
        	<label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        	<input type="password" class="login-field" value="" placeholder="password" id="login-pass" name="password">
        	<label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
        
        <div class="control-group">
        	<input type="password" class="login-field" value="" placeholder="repeat password" id="login-pass" name="passwordCheck">
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