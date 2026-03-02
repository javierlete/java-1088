package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.Dao;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public interface DaoVideo extends Dao<Video> {
	Iterable<VideoListadoDto> obtenerTodosDto();
	Optional<VideoDetalleDto> obtenerPorIdDto(Long id);
	void borrar(Long idUsuario, Long id);
}
