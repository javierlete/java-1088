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

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellidos</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${asistentes}" var="a">
				<tr>
					<th>${a.id}</th>
					<td>${a.nombre}</td>
					<td>${a.apellidos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>