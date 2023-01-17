<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除画面</title>
</head>
<body>
	<h1>削除する図書のメールアドレスを入力してください。</h1>
	<form action="Kadai_1_DeleteServlet" method="post">
		メール：<input type="email" name="email"><br>
			<a href="index.html">戻る</a>
			<input type="submit" value="削除">
	</form>
</body>
</html>