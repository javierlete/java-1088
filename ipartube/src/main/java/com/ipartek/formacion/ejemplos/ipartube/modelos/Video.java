package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.time.LocalDateTime;

public record Video(Long id, String titulo, String descripcion, String imagenUrl, LocalDateTime fecha, String videoUrl, Usuario usuario) {

}
