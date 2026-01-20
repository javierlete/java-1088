<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de asistentes</title>
</head>
<body>
	<ul>
		<c:forEach items="${asistentes}" var="asistente">
			<li>${asistente.id}&nbsp;${asistente.nombre}&nbsp;${asistente.apellidos}</li>
		</c:forEach>
	</ul>
</body>
</html>