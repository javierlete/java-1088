<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<form action="login-post" method="post">
		<div>
			<input name="email" placeholder="Correo electrónico" value="${email}">
		</div>
		<div>
			<input name="password" placeholder="Contraseña">
		</div>

		<button>Login</button>
		
		<p>${errores}</p>
	</form>

</body>
</html>