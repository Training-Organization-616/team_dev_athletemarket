<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更画面</title>
<link rel="stylesheet" href="css/registItem.css">
</head>
<body>

	<h1 style="text-align : center;">会員情報変更</h1>

	<div class="Form">

		<form action="/team_dev_athletemarket/UserServlet?action=updateCheck"
			method="post">

			<c:if test="${not empty message}">
				<p>${message}</p>
				<%--エラーメッセージを出力--%>
			</c:if>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>名前</p>
				<input type="text" class="Form-Item-Input" name="name1" placeholder="名前" value="${loginUser.name}">
			</div> 
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>住所</p>
				<input type="text" class="Form-Item-Input" name="address1" placeholder="住所" value="${loginUser.address}">
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>電話番号</p>
				<input type="text" class="Form-Item-Input" name="tel1" placeholder="電話番号"value="${loginUser.tel}"> 
			</div>
			
			<div class="Form-Item">	
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>生年月日</p>
				<input type="DATE" class="Form-Item-Input" id="birth_day" name="birth_day1" placeholder="生年月日" value="${loginUser.birthDay}">
				<script>
  					document.addEventListener("DOMContentLoaded", () => {
    				const today = new Date().toISOString().split("T")[0];
    				document.getElementById("birth_day").setAttribute("max", today);});
				</script>
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>メールアドレス</p>
				<input type="email" class="Form-Item-Input" name="email1" placeholder="メールアドレス" value="${loginUser.email }">
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>パスワード</p>
				<input type="password" class="Form-Item-Input" name="password1" placeholder="パスワード"> 
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>確認パスワード</p>
				<input type="password" class="Form-Item-Input" name="passwordCheck1" placeholder="確認パスワード">
			</div>

			<button class="Form-Btn">確定</button>

		</form>

		<form action="/team_dev_athletemarket/ItemServlet" method="get">

			<button class="Form-BtnBack">戻る</button>

		</form>

	</div>

</body>
</html>