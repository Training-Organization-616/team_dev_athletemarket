<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報変更</title>
<link rel="stylesheet" href="css/registItem.css">
</head>
<body>

	<h1 style="text-align: center;">商品情報変更</h1>

	<p style="color: red;">${failure}</p>

	<div class="Form">
		<form action="/team_dev_athletemarket/ItemServlet?itemId=${bean.id}"
			method="post" onsubmit="return check()">

			<div class="Form-Item">
				<p class="Form-Item-Label">
					<span class="Form-Item-Label-Required">必須</span>商品名
				</p>
				<input type="text" name="name" id="name" value="${bean.itemName}"
					class="Form-Item-Input" placeholder="例）サッカーボール" required>
			</div>

			<div class="Form-Item">
				<p class="Form-Item-Label">
				<span class="Form-Item-Label-Required">必須</span>カテゴリー名
				</p>
				<select name="category_id" id="category" class="Form-Item-Input">
					<option value="1" ${bean.categoryName == "硬式野球" ? "selected" : ""}>硬式野球</option>
					<option value="2" ${bean.categoryName ==  "軟式野球" ? "selected" : ""}>軟式野球</option>
					<option value="3" ${bean.categoryName == "サッカー" ? "selected" : ""}>サッカー</option>
					<option value="4" ${bean.categoryName == "バスケ" ? "selected" : ""}>バスケ</option>
					<option value="5" ${bean.categoryName == "バレー" ? "selected" : ""}>バレー</option>
					<option value="6" ${bean.categoryName == "ハンドボール" ? "selected" : ""}>ハンドボール</option>
					<option value="7" ${bean.categoryName == "硬式テニス" ? "selected" : ""}>硬式テニス</option>
					<option value="8" ${bean.categoryName == "軟式テニス" ? "selected" : ""}>軟式テニス</option>
					<option value="9" ${bean.categoryName == "ゴルフ" ? "selected" : ""}>ゴルフ</option>
					<option value="10" ${bean.categoryName == "卓球" ? "selected" : ""}>卓球</option>
					<option value="11" ${bean.categoryName == "バトミントン" ? "selected" : ""}>バトミントン</option>
					<option value="12" ${bean.categoryName == "陸上" ? "selected" : ""}>陸上</option>
					<option value="13" ${bean.categoryName == "その他" ? "selected" : ""}>その他</option>
				</select>

			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>値段</p>
				<input type="number" name="price" id="price" min="1" value="${bean.price}" class="Form-Item-Input" placeholder="例）1,000" required>
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label isMsg"><span class="Form-Item-Label-NoneRequired">任意</span>メモ</p>
				<textarea name="memo" rows="5" cols="50" id="memo" class="Form-Item-Textarea"></textarea>
			</div>
			
			<input type="hidden" name="action" value="editConfirm">
			<button class="Form-Btn">変更</button>
		</form>
	</div>
	
	<p style="text-align : center;"><a href="/team_dev_athletemarket/ItemServlet">商品一覧に戻る</a></p>
	
</body>
<script>
	function check() {
        // 商品名
		let name = document.getElementById('name').value;
        // カテゴリー名
        let category = document.getElementById('category');
        category = category.options[category.selectedIndex].textContent;
        // 値段
		let price = document.getElementById('price').value;
        // メモ
        let memo = document.getElementById('memo').value;
		if(window.confirm("商品名："+ name +"\n\nカテゴリー："+ category +"\n\n値段："+ price +"円\n\nメモ(任意)："+ memo +"\n\n" +"                           " + "以上の内容で変更します。よろしいですか？")) {
			var result= alert("商品情報を変更しました");
		return result; 
		}else{
			return false;
		}
	}
</script>
</html>