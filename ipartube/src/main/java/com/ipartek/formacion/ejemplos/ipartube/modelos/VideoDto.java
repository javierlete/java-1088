package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.util.ArrayList;

public record VideoDto(Video video, Comentario comentarioPadre, ArrayList<Comentario> comentarios) {

}
