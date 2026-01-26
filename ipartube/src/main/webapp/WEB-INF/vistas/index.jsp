<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1 class="text-center">Listado de videos ${param.id != null ? 'de' : ''} ${param.id != null ? videos[0].usuario.email : '' }</h1>

<hr>

<div class="row">

	<nav class="col-lg-auto border-end">
		<h2 class="lead">Canales</h2>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link ${param.id == null ? 'fw-medium' : ''}" href="index">TODOS</a></li>
			<c:forEach items="${usuarios}" var="u">
				<li class="nav-item"><a class="nav-link ${u.id == param.id ? 'fw-medium' : ''}" href="usuario?id=${u.id}">${u.email}</a></li>
			</c:forEach>
		</ul>
	</nav>

	<div class="col">
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<c:forEach items="${videos}" var="v">
				<div class="col">
					<div class="card h-100">
						<img src="${v.imagenUrl}" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">${v.titulo}</h5>
							<p class="card-text">${v.descripcion}</p>
							<a class="btn btn-primary stretched-link" href="video?id=${v.id}">Ver
								video ${v.titulo}</a>
						</div>
						<div class="card-footer">
							<small class="text-body-secondary">${v.usuario.email}
								${v.fecha}</small>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>