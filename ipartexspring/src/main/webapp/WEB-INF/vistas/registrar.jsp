<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form:form modelAttribute="usuario" action="registrar-post"
	method="post" class="needs-validation" novalidate="novalidate">
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm">
			<form:input type="text" required="required" maxlength="20"
				class="form-control" cssErrorClass="form-control is-invalid"
				path="nombre" />
			<div class="invalid-feedback">El nombre es obligatorio y debe
				tener como máximo 20 caracteres</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="email" class="col-sm-2 col-form-label">Correo
			electrónico</label>
		<div class="col-sm">
			<form:input type="email" required="required" maxlength="255"
				class="form-control" cssErrorClass="form-control is-invalid"
				path="email"/>
			<div class="invalid-feedback">El correo electrónico debe estar
				rellenado y ser válido y tener como máximo 255 caracteres</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
		<div class="col-sm">
			<form:input type="password" required="required" maxlength="100"
				class="form-control" cssErrorClass="form-control is-invalid"
				path="password"/>
			<div class="invalid-feedback">La contraseña es obligatoria y
				tener como máximo 100 caracteres</div>
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

</form:form>

<script src="../js/validacion.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>