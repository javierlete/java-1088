<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="admin/index">
	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm">
			<input readonly type="number" class="form-control bg-secondary-subtle" id="id">
		</div>
	</div>
	<div class="row mb-3">
		<label for="titulo" class="col-sm-2 col-form-label">Título</label>
		<div class="col-sm">
			<input type="text" class="form-control" id="titulo">
		</div>
	</div>
	<div class="row mb-3">
		<label for="fecha" class="col-sm-2 col-form-label">Fecha</label>
		<div class="col-sm">
			<input type="datetime-local" class="form-control" id="fecha">
		</div>
	</div>
	<div class="row mb-3">
		<label for="imagen" class="col-sm-2 col-form-label">Imagen</label>
		<div class="col-sm">
			<input type="url" class="form-control" id="imagen">
		</div>
	</div>
	<div class="row mb-3">
		<label for="video" class="col-sm-2 col-form-label">Video</label>
		<div class="col-sm">
			<input type="url" class="form-control" id="video">
		</div>
	</div>
	<div class="row mb-3">
		<label for="descripcion" class="col-sm-2 col-form-label">Descripción</label>
		<div class="col-sm">
			<textarea class="form-control" id="descripcion"></textarea>
		
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<button class="btn btn-primary">Guardar</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>