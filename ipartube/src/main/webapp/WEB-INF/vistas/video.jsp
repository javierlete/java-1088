<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<c:set var="video" value="${videodto.video}" />
<c:set var="padre" value="${videodto.comentarioPadre}" />
<c:set var="comentarios" value="${videodto.comentarios}" />

<div class="row">
	<div class="col-md-10 offset-md-1">
		<div class="card mb-3">
			<div class="ratio ratio-16x9 card-img-top">
				<iframe class="" src="${video.videoUrl}"
					title="YouTube video player"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
					referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
			</div>
			<div class="card-body">
				<h5 class="card-title">${video.titulo}</h5>
				<p class="card-text">${video.descripcion}</p>
				<p class="card-text">
					<small class="text-body-secondary">${video.usuario.nombre}
						<javatime:format value="${video.fecha}" style="MS" />
					</small>
				</p>
			</div>
		</div>

		<ul id="comentarios" class="list-group">
			<c:if test="${padre != null}">
				<li
					class="pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch border-primary border border-1">
					<div class="mb-2 w-100 lh-lg">
						<a href="video?id=${video.id}"><i
							class="bi bi-arrow-90deg-left"></i> Volver al listado de
							comentarios raíz</a>
					</div> <img width="25" class="rounded-circle"
					src="${padre.usuario.imagenUrl}" alt="">
					<div
						class="flex-grow-1 d-flex justify-content-start align-items-center">
						<div class="ms-2 me-auto">
							<div class="fw-bold">${padre.usuario.nombre}</div>
						</div>
						<small class="text-secondary"><javatime:format
								value="${padre.fecha}" style="MS" /></small>
					</div>
					<div class="mt-2 w-100 lh-lg">${padre.texto}</div>
					<div>
						<a
							class="badge rounded-pill text-bg-secondary link-underline link-underline-opacity-0"
							href="video?id=${video.id}&comentario=${padre.id}">${padre.respuestas}
							respuestas</a>
					</div>
				</li>
			</c:if>
			<c:if test="${sessionScope.usuario != null}">
				<li
					class="pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch">
					<img width="25" class="rounded-circle"
					src="${sessionScope.usuario.imagenUrl}" alt="">
					<div
						class="flex-grow-1 d-flex justify-content-start align-items-center">
						<div class="ms-2 me-auto">
							<div class="fw-bold">${sessionScope.usuario.nombre}</div>
						</div>
						<small class="text-secondary"><javatime:format
								value="${ahora}" style="MS" /></small>
					</div>
					<form action="usuario/video/comentar" method="post"
						class="w-100 mt-3">
						<input type="hidden" name="id-video" value="${video.id}">
						<input type="hidden" name="id-comentario-padre" value="${padre.id}">
						<textarea name="texto" rows="3" class="form-control lh-lg"
							placeholder="Pon tu comentario aquí"></textarea>
						<div class="w-100 mt-3 text-end">
							<button class="btn btn-primary">Comentar</button>
						</div>
					</form>
				</li>
			</c:if>
			<c:forEach items="${comentarios}" var="c">
				<li
					class="pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch">
					<img width="25" class="rounded-circle" src="${c.usuario.imagenUrl}"
					alt="">
					<div
						class="flex-grow-1 d-flex justify-content-start align-items-center">
						<div class="ms-2 me-auto">
							<div class="fw-bold">${c.usuario.nombre}</div>
						</div>
						<small class="text-secondary"><javatime:format
								value="${c.fecha}" style="MS" /></small>
					</div>
					<div class="mt-2 w-100 lh-lg">${c.texto}</div>
					<div>
						<a
							class="badge rounded-pill text-bg-secondary link-underline link-underline-opacity-0"
							href="video?id=${video.id}&comentario=${c.id}#comentarios">${c.respuestas}
							respuestas</a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>