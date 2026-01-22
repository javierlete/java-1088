<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-hover table-bordered">
	<thead class="table-secondary">
		<tr>
			<th>Id</th>
			<th>Título</th>
			<th>Fecha</th>
			<th>Imagen</th>
			<th>OPCIONES</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach begin="1" end="5" var="i">
			<tr>
				<th>${i}</th>
				<td>Un video superchuli ${i}</td>
				<td>21/01/2026 8:3${i}</td>
				<td><img src="https://picsum.photos/100/75?${i}" alt=""></td>
				<td><a class="btn btn-sm btn-primary" href="admin/formulario?id=${i}"><i
						class="bi bi-pencil-fill"></i></a> <a onclick="javascript:return confirm('¿Estás seguro de borrar el video ${i}?')" class="btn btn-sm btn-danger"
					href="admin/index"><i class="bi bi-trash-fill"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot class="table-secondary">
		<tr>
			<td colspan="4"></td>
			<td><a class="btn btn-sm btn-primary" href="admin/formulario"><i
					class="bi bi-plus-lg"></i></a></td>
		</tr>

	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>