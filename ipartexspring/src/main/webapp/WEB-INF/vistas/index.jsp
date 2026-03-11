<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<sec:authorize access="isAuthenticated()">
	<form class="mb-5" action="usuario/nuevo-mensaje" method="post">
		<textarea class="form-control mb-3" name="texto" placeholder="Mensaje"></textarea>

		<button class="btn btn-primary">Enviar</button>
	</form>
</sec:authorize>

<ul class="list-group list-group">
	<c:forEach items="${mensajes}" var="m">
		<li
			class="list-group-item d-flex justify-content-between align-items-start">
			<div class="ms-2 me-auto">
				<div class="fw-bold">${m.usuario.nombre}</div>
				<pre>${m.texto}</pre>
			</div> <span class="badge text-bg-primary rounded-pill"><javatime:format
					value="${m.fechaHora}" pattern="dd/MM/yyyy HH:mm" /></span>
		</li>
	</c:forEach>
</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
