<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de JSP</title>
</head>
<body>

	<h1>Ejemplo de JSP</h1>

	<%
	for (int i = 1; i <= 3; i++) {
	%>
	<p>
		Hoy estamos a
		<%=LocalDate.now()%>
	</p>
	<%
	}
	%>

</body>
</html>