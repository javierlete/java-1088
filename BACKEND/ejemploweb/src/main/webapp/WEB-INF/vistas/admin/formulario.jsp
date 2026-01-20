<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<form action="formulario" method="post">
		<input name="id" placeholder="Id" readonly value="${asistente.id}">
		<input name="nombre" placeholder="Nombre" value="${asistente.nombre}">
		<input name="apellidos" placeholder="Apellidos" value="${asistente.apellidos}">
		
		<button>Guardar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
