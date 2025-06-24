<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更画面</title>
</head>
<body>

<h1>スポーツ用品CtoC売買サイト</h1>

<form action="/team_dev_athletemarket/UserServlet?action=updateCheck" method="post">

<c:if test="${not empty message}">
<p>${message}</p>
<!--エラーメッセージを出力-->
</c:if>

<input type="text" name="name1" placeholder="名前" value="${loginUser.name}">
<br>

<input type="text" name="address1" placeholder="住所" value="${loginUser.address }">
<br>

<input type="text" name="tel1" placeholder="電話番号" value="${loginUser.tel }">
<br>

<input type="DATE" id="birth_day" name="birth_day1" placeholder="生年月日" value="${loginUser.birthDay }">
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const today = new Date().toISOString().split("T")[0];
    document.getElementById("birth_day").setAttribute("max", today);
  });
</script>
<br>

<input type="email" name="email1" placeholder="メールアドレス" value="${loginUser.email }">
<br>

<input type="password" name="password1" placeholder="パスワード">
<br>

<input type="password" name="passwordCheck1" placeholder="確認パスワード">
<br>

<button>確定</button>

</form>

<form action="/team_dev_athletemarket/ItemServlet" method="get">

<button>戻る</button>

</form>

</body>
</html>