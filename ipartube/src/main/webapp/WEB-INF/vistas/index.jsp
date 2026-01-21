<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1 class="text-center">Listado de videos</h1>

<hr>

<div class="row row-cols-1 row-cols-md-3 g-4">
	<c:forEach items="${videos}" var="v">
		<div class="col">
			<div class="card h-100">
				<img src="${v.imagenUrl}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<h5 class="card-title">${v.titulo}</h5>
					<p class="card-text">${v.descripcion}</p>
					<a class="btn btn-primary" href="video?id=${v.id}">Ver video ${v.titulo}</a>
				</div>
				<div class="card-footer">
					<small class="text-body-secondary">${v.fecha}</small>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>