<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Kadai_1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		Kadai_1 kadai_1 = (Kadai_1)session.getAttribute("input_data");
	%>
	名前：<%=kadai_1.getName() %><br>
	年齢：<%=kadai_1.getAge() %><br>
	性別：<%=kadai_1.getGender() %><br>	
	電話番号：<%=kadai_1.getPhone_number() %><br>
	メール：<%=kadai_1. getMail()%><br>
	パスワード：********<br>
	<a href="Kadai_1_RegisterFormServlet">戻る</a>
	<a href="Kadai_1_RegisterExecuteServlet">OK</a><br>
</body>
</html>