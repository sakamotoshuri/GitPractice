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
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Kadai_1 ka = (Kadai_1)session.getAttribute("input_data");
	%>
		<p style="color:red">登録に失敗しました。</p>
		<h3>新規会員登録</h3>
		<form action="Kadai_1_RegisterConfirmServlet" method="post">
			名前：<input type="text" name="name" value="<%=ka.getName()%>"><br>
			年齢：<input type="number" name="age" value="<%=ka.getAge()%>"><br>
			性別：<br>
			<input type="radio" name="gender" value="男>">男
			<input type="radio" name="gender" value="女">女<br>
			電話番号：<input type="tel" name="phone_number" value="<%=ka.getPhone_number()%>>"><br>
			メール：<input type="email" name="email" value="<%=ka.getMail()%>"><br>
			パスワード：<input type="password" name="pw"><br>
				<a href="index.html">戻る</a>
			<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
	<form action="Kadai_1_RegisterConfirmServlet" method="post">
		名前：<input type="text" name="name"><br>
		年齢：<input type="number" name="age"><br>
		性別：<br>
		<input type="radio" name="gender" value="男">男
		<input type="radio" name="gender" value="女">女<br>
		電話番号：<input type="tel" name="phone_number"><br>
		メール：<input type="email" name="email"><br>
		パスワード：<input type="password" name="pw"><br>
			<a href="index.html">戻る</a>
		<input type="submit" value="登録">
		
	</form>
	<%
		}
	%>
</body>
</html>