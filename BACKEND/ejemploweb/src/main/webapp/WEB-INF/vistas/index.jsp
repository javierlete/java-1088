<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.ejemplos.modelos.Asistente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
@SuppressWarnings("unchecked")
var asistentes = (ArrayList<Asistente>) request.getAttribute("asistentes");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de asistentes</title>
</head>
<body>

	<p><%=request.getAttribute("asistentes")%></p>

	<ul>
		<%
		for (Asistente asistente : asistentes) {
		%>
		<li><%=asistente.id()%> <%=asistente.nombre()%> <%=asistente.apellidos()%></li>
		<%
		}
		%>
	</ul>

</body>
</html>