<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員一覧</title>
</head>
<body>
<c:if test="${customerlist == null}">
<h2>該当データが存在しません</h2>
<br>
</c:if>
<form action="/team_dev_athletemarket/AdminServlet?action=search" method="post">
ID:<input type="text" name="userId" > 
名前：<input type="text" name="name" > 
<button>検索</button>
</form>

<c:if test="${customerlist != null}">
   <table border="1">
    <tr><th>ID</th><th>名前</th><th>住所</th><th>電話番号</th><th>生年月日</th><th>メールアドレス</th><th>入会年月日</th><th>削除キー</th></tr>

    <c:forEach items="${customerlist}" var="customer">
    <tr>
    <form action="/team_dev_athletemarket/AdminServlet" method="post">
        <td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.tel}</td><td>${customer.birthDay}</td>
        <td>${customer.email}</td><td>${customer.startDay}</td>
        <td>
        <input type="hidden" name="id" value="${customer.id}">
        <button type="hidden" name="action" value="delete">削除</button>
        </td>
         
    </form>
    
    </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>