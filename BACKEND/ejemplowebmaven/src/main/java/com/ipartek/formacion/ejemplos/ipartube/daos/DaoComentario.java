package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.Dao;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;

public interface DaoComentario extends Dao<Comentario> {
	Iterable<ComentarioDto> obtenerPorVideoDto(Long id);
	Iterable<ComentarioDto> obtenerPorPadreDto(Long id);
	Optional<ComentarioDto> obtenerPorIdDto(Long id);
}
