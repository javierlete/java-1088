<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="admin/guardar" method="post">
	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm">
			<input readonly type="number" class="form-control bg-secondary-subtle" id="id" name="id" value="${video.id}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="usuario" class="col-sm-2 col-form-label">Usuario</label>
		<div class="col-sm">
			<select class="form-select" id="usuario" name="usuario">
				<c:forEach items="${usuarios}" var="u">
					<option ${u.id == video.usuario.id ? 'selected' : ''} value="${u.id}">${u.email}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row mb-3">
		<label for="titulo" class="col-sm-2 col-form-label">Título</label>
		<div class="col-sm">
			<input type="text" class="form-control" id="titulo" name="titulo" value="${video.titulo}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="fecha" class="col-sm-2 col-form-label">Fecha</label>
		<div class="col-sm">
			<input type="datetime-local" class="form-control" id="fecha" name="fecha" value="${video.fecha}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="imagen" class="col-sm-2 col-form-label">Imagen</label>
		<div class="col-sm">
			<input type="url" class="form-control" id="imagen" name="imagen" value="${video.imagenUrl}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="video" class="col-sm-2 col-form-label">Video</label>
		<div class="col-sm">
			<input type="url" class="form-control" id="video" name="video" value="${video.videoUrl}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="descripcion" class="col-sm-2 col-form-label">Descripción</label>
		<div class="col-sm">
			<textarea class="form-control" id="descripcion" name="descripcion">${video.descripcion}</textarea>
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<button class="btn btn-primary">Guardar</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>