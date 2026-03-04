package com.ipartek.formacion.ejemplos.ipartube.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public interface PublicoNegocio {
	Iterable<Usuario> listadoUsuarios();
	Iterable<Video> listadoVideos();
	Optional<VideoDto> detalleVideo(Long id);
	Optional<VideoDto> detalleVideo(Long idVideo, Long idComentario);
}
