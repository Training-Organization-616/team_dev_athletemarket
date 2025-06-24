<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スポーツ用品CtoC売買サイト</title>
	<link rel="stylesheet" href="css/registItem.css">
</head>
<body>

	<h1 style="text-align : center;">商品登録</h1>

		<p style="color: red;">${failure}</p>

		<div class="Form">
			<form action="/team_dev_athletemarket/ItemServlet" method="post" onsubmit="return check()">
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>商品名</p>
					<input type="text" name="name" id="name" class="Form-Item-Input" placeholder="例）サッカーボール" required>
			</div>
			
			<div class="Form-Item">
				<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>カテゴリー名</p>
					<select name="category_id" id="category" class="Form-Item-Input">
						<option value="1" ${category_id == 1 ? "selected" : ""}>硬式野球</option>
						<option value="2" ${category_id == 2 ? "selected" : ""}>軟式野球</option>
						<option value="3" ${category_id == 3 ? "selected" : ""}>サッカー</option>
						<option value="4" ${category_id == 4 ? "selected" : ""}>バスケ</option>
						<option value="5" ${category_id == 5 ? "selected" : ""}>バレー</option>
						<option value="6" ${category_id == 6 ? "selected" : ""}>ハンドボール</option>
						<option value="7" ${category_id == 7 ? "selected" : ""}>硬式テニス</option>
						<option value="8" ${category_id == 8 ? "selected" : ""}>軟式テニス</option>
						<option value="9" ${category_id == 9 ? "selected" : ""}>ゴルフ</option>
						<option value="10" ${category_id == 10 ? "selected" : ""}>卓球</option>
						<option value="11" ${category_id == 11 ? "selected" : ""}>バドミントン</option>
						<option value="12" ${category_id == 12 ? "selected" : ""}>陸上</option>
						<option value="13" ${category_id == 13 ? "selected" : ""}>その他</option>
					</select>		
			</div>
					
			<div class="Form-Item">
    			<p class="Form-Item-Label"><span class="Form-Item-Label-Required">必須</span>値段</p>
				<input type="number" name="price" id="price" min="1" class="Form-Item-Input" placeholder="例）1,000" required>
			</div>		
							
					
			<div class="Form-Item">
    			<p class="Form-Item-Label isMsg"><span class="Form-Item-Label-NoneRequired">任意</span>メモ</p>
				<textarea name="memo" rows="5" cols="50" id="memo" class="Form-Item-Textarea"></textarea>		
			</div>
				<input type="hidden" name="action" value="confirm">
				<button class="Form-Btn">出品</button>
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
		var result = window.confirm("商品名："+ name +"\n\nカテゴリー："+ category +"\n\n値段："+ price +"円\n\nメモ(任意)："+ memo +"\n\n" +"                           " + "以上の内容で出品します。よろしいですか？");
		return result; 
	}
</script>
</html>