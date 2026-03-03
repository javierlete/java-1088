package com.ipartek.formacion.ejemplos.ipartube.dtos;

public record VideoDto(VideoDetalleDto video, ComentarioDto comentarioPadre, Iterable<ComentarioDto> comentarios) {

}
