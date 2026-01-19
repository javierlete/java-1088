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

	<form action="formulario" method="post">
		<input name="id" placeholder="Id" readonly value="${asistente.id}">
		<input name="nombre" placeholder="Nombre" value="${asistente.nombre}">
		<input name="apellidos" placeholder="Apellidos" value="${asistente.apellidos}">
		
		<button>Guardar</button>
	</form>

</body>
</html>