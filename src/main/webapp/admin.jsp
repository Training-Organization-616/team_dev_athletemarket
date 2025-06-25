<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員一覧</title>
<link rel="stylesheet" href="css/admins.css">
</head>
<body>
<!--追加-->

<div class="hgroup">
<h1>アスリートマーケット</h1>
	<p>こんにちは、${loginUser.name}さん 管理者用</p>
<!--<h1><a href="/team_dev_athletemarket/AdminServlet?action=">会員一覧検索</a></h1>-->
<h1>会員一覧検索</h1>

<c:if test="${message != null}">
<font color="red">${message}</font>
<br>
</c:if>

<!--追加-->
<!--会員一覧リンク-->
<a href="/team_dev_athletemarket/AdminServlet" method="post">
会員一覧</a>
<!--商品一覧リンク チェック-->
<a href="/team_dev_athletemarket/AdminServlet?action=adminItems" method="post">
商品一覧</a>
<!--追加-->


<a href="/team_dev_athletemarket/LoginServlet?action=logout" method="post">
ログアウト</a>
<form action="/team_dev_athletemarket/AdminServlet?action=search" method="post">
ID:<input type="text" name="userId" > <br>
名前：<input type="text" name="name" > <br>
<button>検索</button>
</form>

<c:if test="${customerlist != null}">
   <table border="1">
    <tr><th>ID</th><th>名前</th><th>住所</th><th>電話番号</th><th>生年月日</th><th>メールアドレス</th><th>入会年月日</th><th>管理</th></tr>

    <c:forEach items="${customerlist}" var="customer">
    <tr>
    <form action="/team_dev_athletemarket/AdminServlet" method="post" onsubmit="return beforeSubmit()">
        <td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.tel}</td><td>${customer.birthDay}</td>
        <td>${customer.email}</td><td>${customer.startDay}</td>
        <td>
        <input type="hidden" name="id" value="${customer.id}">
        <button name="action" value="delete" id="btn" type="submit">強制退会</button>
        </td>
         
    </form>
   
    
    </tr>
    </c:forEach>
    </table>
</c:if>

<script>
  function beforeSubmit() {
    if(window.confirm('本当に強制退会させますか？')) {
      return true;
    } else {
      return false;
    }
  }
</script>
</body>
</html>