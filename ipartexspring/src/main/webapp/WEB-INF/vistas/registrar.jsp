<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="registrar-post" method="post" class="needs-validation" novalidate>
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm">
			<input type="text" required maxlength="20" class="form-control ${errores.nombre != null ? 'is-invalid' : ''}" id="nombre" name="nombre"
				value="${usuario.nombre}">
			<div class="invalid-feedback">El nombre es obligatorio y debe tener como máximo 20 caracteres</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="email" class="col-sm-2 col-form-label">Correo
			electrónico</label>
		<div class="col-sm">
			<input type="email" required maxlength="255" class="form-control ${errores.email != null ? 'is-invalid' : ''}" id="email" name="email"
				value="${usuario.email}">
			<div class="invalid-feedback">El correo electrónico debe estar rellenado y ser válido y tener como máximo 255 caracteres</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
		<div class="col-sm">
			<input type="password" required maxlength="100" class="form-control ${errores.password != null ? 'is-invalid' : ''}" id="password"
				name="password" value="${usuario.password}">
			<div class="invalid-feedback">La contraseña es obligatoria y tener como máximo 100 caracteres</div>
		</div>
	</div>

	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<button class="btn btn-primary">Registrarse</button>
			<ul class="mt-3">
				<c:forEach items="${errores}" var="e">
					<li class="text-danger"><strong>${e.key}</strong> ${e.value}</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</form>

<script src="../js/validacion.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>