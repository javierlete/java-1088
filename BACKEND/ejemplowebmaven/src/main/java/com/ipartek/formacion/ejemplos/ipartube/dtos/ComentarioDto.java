package com.ipartek.formacion.ejemplos.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioDto(Long id, String texto, LocalDateTime fecha, Long usuarioId, String usuarioNombre) {

}
