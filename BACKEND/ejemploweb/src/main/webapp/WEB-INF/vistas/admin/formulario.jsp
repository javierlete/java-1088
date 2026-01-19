<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de administración de asistentes</title>
</head>
<body>

	<p>${asistente}</p>

	<form>
		<input placeholder="Id" readonly value="${asistente.id}">
		<input placeholder="Nombre" value="${asistente.nombre}">
		<input placeholder="Apellidos" value="${asistente.apellidos}">
		
		<button>Guardar</button>
	</form>

</body>
</html>