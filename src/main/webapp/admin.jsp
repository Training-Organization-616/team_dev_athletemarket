<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員一覧</title>
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
<!--追加-->

<div class="hgroup">
	<h1>アスリートマーケット(管理者用ページ)</h1>
<!--	追加-->
<!--	<p>こんにちは、${loginUser.name}さん 管理者用<br><a href="/team_dev_athletemarket/UserServlet?action=update">会員情報</a>  <a href="/team_dev_athletemarket/ItemServlet?action=mypage">マイページ</a>  <a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a></p>-->
	<p>こんにちは、${loginUser.name}(管理者)さん<br>
	<a href="/team_dev_athletemarket/AdminServlet" method="post">会員一覧</a> 
	<a href="/team_dev_athletemarket/AdminServlet?action=adminItems" method="post">商品一覧</a>
	<a href="/team_dev_athletemarket/LoginServlet?action=logout">ログアウト</a>
	</p>
</div>


<div class="search-box">
<h1>会員一覧検索</h1>

<c:if test="${message != null}">
<font color="red">${message}</font>
<br>
</c:if>

<form action="/team_dev_athletemarket/AdminServlet?action=search" method="post">
<label for="UserId"></label><input type="text" name="userId" placeholder="ID検索">
<label for="name"></label><input type="text" name="name" placeholder="名前検索">
<button>検索</button>
</form>
</div>

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
<button name="action" value="delete" id="btn" type="submit">⚠ 強制退会</button>

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