<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

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
					<small class="text-body-secondary">${video.usuario.email}
						${video.fecha}</small>
				</p>
			</div>
		</div>

		<ul class="list-group">
			<li class="pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch">
				<img class="rounded-circle" src="https://picsum.photos/25/25?${i}"
				alt="">
				<div
					class="flex-grow-1 d-flex justify-content-start align-items-center">
					<div class="ms-2 me-auto">
						<div class="fw-bold">[USUARIO ${i}]</div>
					</div>
					<small class="text-secondary">27
						ene 2026 8:27</small>
				</div>
				<div class="w-100 mt-3"><textarea rows="3" class="form-control lh-lg" placeholder="Comenta el video aquí"></textarea></div>
				<div class="w-100 mt-3 text-end"><button class="btn btn-primary">Comentar</button></div>
			</li>
			<c:forEach begin="1" end="10" var="i">
				<li class="pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch">
					<img class="rounded-circle" src="https://picsum.photos/25/25?${i}"
					alt="">
					<div
						class="flex-grow-1 d-flex justify-content-start align-items-center">
						<div class="ms-2 me-auto">
							<div class="fw-bold">[USUARIO ${i}]</div>
						</div>
						<small class="text-secondary">${i}
							ene 2026 8:27</small>
					</div>
					<div class="mt-2 w-100 lh-lg">[COMENTARIO ñalsdjkfñ alsghd
						lñgjasñlfhlñasdhk gñlkjas dñlfh añsldkgj ñalsdkhf ñlaskjd glñkha
						sdñlfkj asdlñkbgh ñlasdkjf lñasdkh gñlajksd ñflhsdlñghk añsdfj
						lñasdhg ñlasdj hs dg ${i}]</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>