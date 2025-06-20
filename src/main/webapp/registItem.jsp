<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スポーツ用品CtoC売買サイト</title>
<%-- <link rel="stylesheet" href="css/style.css"> --%>
</head>
<body>

	<h1 style="background-color : blue;">スポーツ用品CtoC売買サイト</h1>

		<p style="color: red;">${failure}</p>

			<form action="/team_dev_athletemarket/ItemServlet"
				method="post" onsubmit="return check()">
				<table>
					<tr>
						<th>商品名：</th>
						<td><input type="text" name="name" id="name" required></td>
					</tr>
					<tr>
						<th>カテゴリー：</th>
						<td>
						<select name="category_id" id="category">
						<option value="1" selected>硬式野球</option>
						<option value="2">軟式野球</option>
						<option value="3">サッカー</option>
						<option value="4">バスケ</option>
						<option value="5">バレー</option>
						<option value="6">ハンドボール</option>
						<option value="7">硬式テニス</option>
						<option value="8">軟式テニス</option>
						<option value="9">ゴルフ</option>
						<option value="10">卓球</option>
						<option value="11">バドミントン</option>
						<option value="12">陸上</option>
						<option value="13">その他</option>
						</select>
						</td>
					</tr>
					<tr>
					
						<th>値段：</th>
						<td>
						<input type="number" name="price" id="price" min="1" required>
						</td>
							
					</tr>
					<tr>
						<th>メモ(任意)：</th>
						<td>
						<textarea name="memo" rows="5" cols="50" id="memo"></textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="confirm">
				<button>出品</button>
			</form>
			<br><a href="/team_dev_athletemarket/ItemServlet">商品一覧に戻る</a>
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