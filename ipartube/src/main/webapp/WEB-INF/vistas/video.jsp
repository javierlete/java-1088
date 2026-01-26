<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<div class="col-md-10 offset-md-1">
		<div class="card mb-3">
			<div class="ratio ratio-16x9 card-img-top">
				<iframe class=""
					src="${video.videoUrl}"
					title="YouTube video player"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
					referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
			</div>
			<div class="card-body">
				<h5 class="card-title">${video.titulo}</h5>
				<p class="card-text">${video.descripcion}</p>
				<p class="card-text">
					<small class="text-body-secondary">${video.usuario.email} ${video.fecha}</small>
				</p>
			</div>
		</div>
	</div>

</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>