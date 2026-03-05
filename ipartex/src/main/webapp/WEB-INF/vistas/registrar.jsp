<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar</title>
</head>
<body>

	<ul>
		<c:forEach items="${errores}" var="e">
			<li><strong>${e.key}</strong> ${e.value}</li>
		</c:forEach>
	</ul>

	<form action="registrar-post" method="post">
		<div>
			<input name="nombre" placeholder="Nombre" value="${usuario.nombre}">
			<p>${errores.nombre}</p>
		</div>
		<div>
			<input name="email" placeholder="Correo electrónico"
				value="${usuario.email}">
			<p>${errores.email}</p>
		</div>
		<div>
			<input name="password" placeholder="Contraseña">
			<p>${errores.password}</p>
		</div>

		<button>Registrarse</button>
	</form>

</body>
</html>