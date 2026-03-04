package com.ipartek.formacion.ejemplos.ipartube.logicanegocio.impl;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;
import com.ipartek.formacion.ejemplos.ipartube.logicanegocio.PublicoNegocio;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class PublicoNegocioImpl implements PublicoNegocio {
	
	private final DaoVideo DAO_VIDEO = (DaoVideo) Fabrica.getObjeto("dao.video");
	private final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.getObjeto("dao.usuario");
	private final DaoComentario DAO_COMENTARIO = (DaoComentario) Fabrica.getObjeto("dao.comentario");
	
	@Override
	public Iterable<Usuario> listadoUsuarios() {
		return DAO_USUARIO.obtenerTodos();
	}

	@Override
	public Iterable<Video> listadoVideos() {
		return DAO_VIDEO.obtenerTodos();
	}

	@Override
	public Optional<VideoDto> detalleVideo(Long idVideo) {
		return detalleVideo(idVideo, null);
	}
	
	@Override
	public Optional<VideoDto> detalleVideo(Long idVideo, Long idComentario) {
		Optional<VideoDetalleDto> video = DAO_VIDEO.obtenerPorIdDto(idVideo);
		
		if(video.isEmpty()) {
			return Optional.empty();
		}
		
		ComentarioDto comentario = idComentario != null ? DAO_COMENTARIO.obtenerPorIdDto(idComentario).orElse(null)
				: null;
		Iterable<ComentarioDto> comentarios = idComentario == null ? DAO_COMENTARIO.obtenerPorVideoDto(idVideo)
				: DAO_COMENTARIO.obtenerPorPadreDto(idComentario);

		return Optional.of(new VideoDto(video.get(), comentario, comentarios));
	}

}
