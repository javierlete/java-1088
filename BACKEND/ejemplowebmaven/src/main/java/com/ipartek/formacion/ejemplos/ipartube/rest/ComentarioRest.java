package com.ipartek.formacion.ejemplos.ipartube.rest;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/comentarios")
public class ComentarioRest {
	private static final DaoComentario DAO_COMENTARIO= (DaoComentario) Fabrica.getObjeto("dao.comentario");
	
	@GET
	@Path("{id}")
	public ComentarioDto obtenerPorId(@PathParam("id") Long id) {
		return DAO_COMENTARIO.obtenerPorIdDto(id).orElse(null);
	}
	
	@GET
	@Path("{id}/respuestas")
	public Iterable<ComentarioDto> obtenerPorPadre(@PathParam("id") Long id) {
		return DAO_COMENTARIO.obtenerPorPadreDto(id);
	}
}
