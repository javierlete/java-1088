package com.ipartek.formacion.ejemplos.ipartube.rest;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.ComentarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/comentarios")
public class ComentarioRest {
	@GET
	@Path("{id}")
	public ComentarioDto obtenerPorId(@PathParam("id") Long id) {
		return ComentarioCrud.obtenerPorId(id);
	}
	
	@GET
	@Path("{id}/respuestas")
	public List<ComentarioDto> obtenerPorPadre(@PathParam("id") Long id) {
		return ComentarioCrud.obtenerPorPadre(id);
	}
}
