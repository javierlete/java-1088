<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar</title>
</head>
<body>

	<ul>
		<c:forEach items="${errores}" var="e">
			<li><strong>${e.propertyPath}</strong> ${e.message} </li>
		</c:forEach>
	</ul>

	<form action="registrar-post" method="post">
		<input name="nombre" placeholder="Nombre" value="${usuario.nombre}">
		<input name="email" placeholder="Correo electrónico" value="${usuario.email}">
		<input name="password" placeholder="Contraseña" value="${usuario.password}">
		
		<button>Registrarse</button>
	</form>

</body>
</html>