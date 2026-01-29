package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.time.LocalDateTime;

public record Comentario(Long id, Usuario usuario, Video video, LocalDateTime fecha, String texto, Integer respuestas, Comentario comentarioPadre) {

}
