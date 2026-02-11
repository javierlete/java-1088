package com.ipartek.formacion.ejemplos.ipartube.dtos;

import java.time.LocalDateTime;

public record VideoListadoDto(Long id, String titulo, String imagenUrl, LocalDateTime fecha,
		Long usuarioId, String usuarioNombre) {

}
