<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-hover table-bordered">
	<thead class="table-secondary">
		<tr>
			<th>Id</th>
			<th>Título</th>
			<th>Fecha</th>
			<th>Usuario</th>
			<th>Imagen</th>
			<th>OPCIONES</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${videos}" var="v">
			<tr class="align-middle">
				<th>${v.id}</th>
				<td>${v.titulo}</td>
				<td><javatime:format value="${v.fecha}" style="MS" /></td>
				<td>${v.usuario.nombre}</td>
				<td><img class="img-thumbnail" width="75" src="${v.imagenUrl}" alt=""></td>
				<td><a class="btn btn-sm btn-primary" href="admin/formulario?id=${v.id}"><i
						class="bi bi-pencil-fill"></i></a> <a onclick="javascript:return confirm('¿Estás seguro de borrar el video ${i}?')" class="btn btn-sm btn-danger"
					href="admin/borrar?id=${v.id}"><i class="bi bi-trash-fill"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot class="table-secondary">
		<tr>
			<td colspan="5"></td>
			<td><a class="btn btn-sm btn-primary" href="admin/formulario"><i
					class="bi bi-plus-lg"></i></a></td>
		</tr>

	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>