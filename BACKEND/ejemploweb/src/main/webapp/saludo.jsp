<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Saludo</title>
<meta charset="UTF-8">
</head>
<body>
	<%
	String nombre = request.getParameter("nombre");
	
	if (nombre != null) {
	%>
	<h1>
		Hola
		<%=nombre%></h1>
	<%
	}
	%>
	<form>
		<input placeholder="Nombre" name="nombre">

		<button>Saludar</button>
	</form>
</body>
</html>