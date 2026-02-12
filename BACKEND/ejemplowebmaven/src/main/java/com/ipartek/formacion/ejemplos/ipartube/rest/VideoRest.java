package com.ipartek.formacion.ejemplos.ipartube.rest;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.ComentarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

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
	@GET
	public List<VideoListadoDto> obtenerTodos() {
		return VideoCrud.obtenerTodosDto();
	}
	
	@GET
	@Path("{id}")
	public VideoDetalleDto obtenerPorId(@PathParam("id") Long id) {
		VideoDetalleDto video = VideoCrud.obtenerPorIdDto(id);
		
		if(video == null) {
			throw new NotFoundException();
		}
		
		return video;
	}
	
	@GET
	@Path("{id}/comentarios")
	public List<ComentarioDto> obtenerPorIdVideo(@PathParam("id") Long id) {
		return ComentarioCrud.obtenerPorVideo(id);
	}
	
	@POST
	public Response insertar(Video video) {
		return Response.status(Status.CREATED).entity(VideoCrud.insertar(video)).build();
	}
	
	@PUT
	@Path("{id}")
	public Video modificar(@PathParam("id") Long id, Video video) {
		if(id != video.getId()) {
			throw new BadRequestException();
		}
		
		return VideoCrud.modificar(video);
	}
	
	@DELETE
	@Path("{id}")
	public void borrar(@PathParam("id") Long id) {
		VideoCrud.borrar(id);
	}
}
