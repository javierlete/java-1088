package com.ipartek.formacion.ejemplos.ipartube.rest;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import io.dropwizard.auth.Auth;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/videos")
public class VideoRest {
	private static final DaoVideo DAO_VIDEO = (DaoVideo) Fabrica.getObjeto("dao.video");
	private static final DaoComentario DAO_COMENTARIO= (DaoComentario) Fabrica.getObjeto("dao.comentario");
	
	@GET
	public Iterable<VideoListadoDto> obtenerTodos() {
		return DAO_VIDEO.obtenerTodosDto();
	}
	
	@GET
	@Path("{id}")
	public VideoDetalleDto obtenerPorId(@PathParam("id") Long id) {
		Optional<VideoDetalleDto> video = DAO_VIDEO.obtenerPorIdDto(id);
		
		if(video.isEmpty()) {
			throw new NotFoundException();
		}
		
		return video.get();
	}
	
	@GET
	@Path("{id}/comentarios")
	public Iterable<ComentarioDto> obtenerPorIdVideo(@PathParam("id") Long id) {
		return DAO_COMENTARIO.obtenerPorVideoDto(id);
	}
	
	@RolesAllowed("USUARIO")
	@POST
	public Response insertar(Video video) {
		return Response.status(Status.CREATED).entity(DAO_VIDEO.insertar(video)).build();
	}
	
	@RolesAllowed("USUARIO")
	@PUT
	@Path("{id}")
	public Video modificar(@PathParam("id") Long id, Video video) {
		if(id != video.getId()) {
			throw new BadRequestException();
		}
		
		return DAO_VIDEO.modificar(video);
	}
	
	@RolesAllowed("USUARIO")
	@DELETE
	@Path("{id}")
	public void borrar(@Auth Usuario usuario, @PathParam("id") Long id) {
		System.out.println(usuario);
		DAO_VIDEO.borrar(usuario.getId(), id);
	}
}
