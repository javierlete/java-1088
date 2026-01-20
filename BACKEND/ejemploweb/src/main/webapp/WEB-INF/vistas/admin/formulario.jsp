<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
<form class="container" action="admin/formulario" method="post">
	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm">
			<input readonly type="number" class="form-control" id="id" name="id" value="${asistente.id}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm">
			<input type="text" class="form-control" id="nombre" name="nombre" value="${asistente.nombre}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="apellidos" class="col-sm-2 col-form-label">Apellidos</label>
		<div class="col-sm">
			<input type="text" class="form-control" id="apellidos" name="apellidos" value="${asistente.apellidos}">
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<button class="btn btn-primary">Guardar</button>
		</div>
	</div>
	
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
