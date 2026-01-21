<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1 class="text-center">Listado de videos</h1>

<hr>

<div class="row row-cols-1 row-cols-md-3 g-4">
	<c:forEach begin="1" end="9" var="i">
		<div class="col">
			<div class="card h-100">
				<img src="https://picsum.photos/1600/900?${i}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<h5 class="card-title">Video ${i}</h5>
					<p class="card-text">Descripción del video ${i}</p>
					<a class="btn btn-primary" href="video">Ver video ${i}</a>
				</div>
				<div class="card-footer">
					<small class="text-body-secondary">21/01/2026 9:0${i}</small>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>