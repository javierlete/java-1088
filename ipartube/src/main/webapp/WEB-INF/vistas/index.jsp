<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1 class="text-center">Listado de videos ${param.id != null ? 'de' : ''}
	${param.id != null ? videos[0].usuario.nombre : '' }</h1>

<hr>

<div class="row">

	<nav class="col-lg-auto border-end mb-4">
		<h2 class="lead">Canales</h2>
		<ul class="nav flex-column">
			<li class="nav-item"><a
				class="nav-link ${param.id == null ? 'fw-medium' : ''}" href="index">TODOS</a></li>
			<c:forEach items="${usuarios}" var="u">
				<li class="nav-item"><a
					class="nav-link ${u.id == param.id ? 'fw-medium' : ''}"
					href="usuario?id=${u.id}">${u.nombre}</a></li>
			</c:forEach>
		</ul>
	</nav>

	<div class="col">
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<c:forEach items="${videos}" var="v">
				<div class="col">
					<form class="card h-100" action="usuario/video/guardar"
						method="post">
						<input type="hidden" name="id" value="${v.id}">
						<div class="ratio ratio-16x9">
							<img src="${v.imagenUrl}" class="card-img-top" alt="...">
						</div>
						<c:if test="${v.id == param.editar}">
							<input class="form-control" name="imagen" value="${v.imagenUrl}">
						</c:if>
						<div class="card-body">
							<h5 class="card-title">
								<c:choose>
									<c:when test="${v.id == param.editar}">
										<input class="form-control" name="titulo" value="${v.titulo}">
									</c:when>
									<c:otherwise>
										${v.titulo}
									</c:otherwise>
								</c:choose>
							</h5>
							<p class="card-text">
								<c:choose>
									<c:when test="${v.id == param.editar}">
										<textarea name="descripcion" class="form-control">${v.descripcion}</textarea>
									</c:when>
									<c:otherwise>
										${v.descripcion}
									</c:otherwise>
								</c:choose>
							</p>
							<c:choose>
								<c:when test="${v.id == param.editar}">
									<input name="video" class="form-control" value="${v.videoUrl}">
								</c:when>
								<c:otherwise>
									<a class="btn btn-primary stretched-link"
										href="video?id=${v.id}">Ver video ${v.titulo}</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="card-footer" style="z-index: 2">
							<small
								class="text-body-secondary d-flex justify-content-between align-items-baseline">
								<a class="btn btn-outline-primary"
								href="usuario/video/${v.meGusta ? 'no' : ''}megusta?id=${v.id}"><i
									class="bi bi-hand-thumbs-up${v.meGusta ? '-fill' : ''}"></i> ${v.numeroMeGusta}</a> <javatime:format
									value="${v.fecha}" style="MS" /> <a
								href="usuario?id=${v.usuario.id}">${v.usuario.nombre}</a>  <c:if
									test="${param.id == usuario.id}">
									<span class="card-text"> <c:choose>
											<c:when test="${param.editar == null}">
												<a class="btn btn-outline-primary"
													href="usuario?id=${usuario.id}&editar=${v.id}"><i
													class="bi bi-pencil-fill"></i></a>
												<a class="btn btn-outline-danger"
													href="usuario/video/borrar?id=${v.id}"><i
													class="bi bi-trash-fill"></i></a>
											</c:when>
											<c:otherwise>
												<button class="btn btn-outline-primary">
													<i class="bi bi-floppy2-fill"></i>
												</button>
											</c:otherwise>
										</c:choose>
									</span>
								</c:if>
							</small>
						</div>
					</form>
				</div>
			</c:forEach>
			<c:if test="${param.id == usuario.id}">
				<div class="col">
					<form class="card h-100" action="usuario/video/guardar"
						method="post">
						<div class="ratio ratio-16x9">
							<img
								src="${pageContext.request.contextPath}/imgs/plus-circle-fill.svg"
								class="card-img-top p-3" alt="...">
						</div>
						<input name="imagen" class="form-control" placeholder="URL imagen">
						<div class="card-body">
							<h5 class="card-title">
								<input name="titulo" class="form-control" placeholder="Título">
							</h5>
							<p class="card-text">
								<textarea name="descripcion" class="form-control"
									placeholder="Descripción"></textarea>
							</p>
							<input name="video" class="form-control" placeholder="URL video">
						</div>
						<div class="card-footer">
							<small
								class="text-body-secondary d-flex justify-content-between align-items-baseline"><span>${sessionScope.usuario.nombre}
									<javatime:format value="${ahora}" style="MS" />
							</span> <c:if test="${param.id == usuario.id}">
									<span class="card-text" style="z-index: 2">
										<button class="btn btn-outline-primary">
											<i class="bi bi-floppy2-fill"></i>
										</button>
									</span>
								</c:if></small>
						</div>
					</form>
				</div>
			</c:if>
		</div>
	</div>

</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>