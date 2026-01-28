package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.util.ArrayList;

public record VideoDto(Video video, ArrayList<Comentario> comentarios) {

}
