<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
	<li>${usuario.nombre}</li>
	<li><a href="login">Iniciar sesión</a></li>
	<li><a href="registrar">Registrarse</a></li>
</ul>

<form action="usuario/nuevo-mensaje" method="post">
	<textarea name="texto" placeholder="Mensaje"></textarea>
	
	<button>Enviar</button>
</form>

<ul>
	<c:forEach items="${mensajes}" var="m">
		<li>
			<p>${m.usuario.nombre} - <javatime:format value="${m.fechaHora}" pattern="dd/MM/yyyy HH:mm"/></p>
			<pre>${m.texto}</pre>
		</li>
	</c:forEach>
</ul>

</body>
</html>