<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除成功</title>
</head>
<body>
	<h2 style="color:red">下記のデータを削除しました。</h2>
	<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	%>
		<h3>メール：<%=email %></h3>
		<a href="index.html">戻る</a>
</body>
</html>