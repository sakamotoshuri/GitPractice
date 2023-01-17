<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Kadai_1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
</head>
<body>
	<h3>一覧表示</h3>
	<table border="1" style="width: 800px;">
		<tr>
			<th>名前</th>
			<th>年齢</th>
			<th>性別</th>
			<th>電話番号</th>
			<th>メール</th>
		</tr>
	<%
	List<Kadai_1> list = (ArrayList<Kadai_1>)request.getAttribute("list");
			for(Kadai_1 s : list) {
	%>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getAge() %></td>
			<td><%=s.getGender() %></td>
			<td><%=s.getPhone_number() %></td>
			<td><%=s.getMail() %></td>
		</tr>
	<%} %>
	</table>
	<a href="index.html">戻る</a>
</body>
</html>