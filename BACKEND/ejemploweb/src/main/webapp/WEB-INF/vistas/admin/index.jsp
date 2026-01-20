<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<table class="table table-striped table-hover table-bordered">
		<thead class="table-secondary">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${asistentes}" var="a">
				<tr>
					<th>${a.id}</th>
					<td>${a.nombre}</td>
					<td>${a.apellidos}</td>
					<td><a class="btn btn-sm btn-primary" href="admin/editar?id=${a.id}">Editar</a> <a
						onclick="javascript:return confirm('¿Estás seguro de borrar a ${a.nombre} ${a.apellidos}?')"
						class="btn btn-sm btn-danger" href="admin/borrar?id=${a.id}">Borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot class="table-secondary">
			<tr>
				<td colspan="3"></td>
				<td><a class="btn btn-sm btn-primary" href="admin/formulario">Añadir</a></td>
			</tr>
		</tfoot>
	</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>