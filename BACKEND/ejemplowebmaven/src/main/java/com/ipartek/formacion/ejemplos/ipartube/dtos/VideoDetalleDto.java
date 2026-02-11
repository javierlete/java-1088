package com.ipartek.formacion.ejemplos.ipartube.dtos;

import java.time.LocalDateTime;

public record VideoDetalleDto(Long id, String titulo, String descripcion, String videoUrl, LocalDateTime fecha,
		Long usuarioId, String usuarioNombre) {

}
