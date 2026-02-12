package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.ComentarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.RolCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class VideoCrudPruebas {
	public static void main(String[] args) {
		Rol rol = new Rol(null, "USUARIO", "Usuarios");
		RolCrud.insertar(rol);

		Usuario javier = new Usuario(null, null, "Javier", "javier@email.net", "alksdjfl", rol);
		UsuarioCrud.insertar(javier);

		Usuario pepe = new Usuario(null, null, "Pepe", "pepe@email.net", "alksdjfl", rol);
		UsuarioCrud.insertar(pepe);

		Video videoMichael = new Video(null, "Michael Jackson en Auckly",
				"Con efectos 3D de la época.\r\nComo se nota la diferencia...",
				"https://i.ytimg.com/vi/ChrLRauOR28/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/ChrLRauOR28", javier);
		VideoCrud.insertar(videoMichael);

		Video videoClasicosRock = new Video(null, "Clásicos del Rock", null,
				"https://i.ytimg.com/vi/4_O-y3SM3UM/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/4_O-y3SM3UM", pepe);
		VideoCrud.insertar(videoClasicosRock);

		Video video8bbb = new Video(null, "8BBB en directo", "Video de la 8 Bit Bit Band en diciembre de 2025",
				"https://i.ytimg.com/vi/mzjqdhWxg8M/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/mzjqdhWxg8M", javier);
		VideoCrud.insertar(video8bbb);

		for (Video v : VideoCrud.obtenerTodos()) {
			System.out.println(v);
		}

		Comentario primerComentario = new Comentario(null, "¡Primer comentario!", javier, videoMichael,
				LocalDateTime.now(), null, null);
		ComentarioCrud.insertar(primerComentario);

		ComentarioCrud
				.insertar(new Comentario(null, "Yo voy segundo", pepe, videoMichael, LocalDateTime.now(), null, null));
		ComentarioCrud.insertar(new Comentario(null, "¡¡Hombre, como eres el que ha creado el video...!!", pepe,
				videoMichael, LocalDateTime.now(), null, primerComentario));
		
		for(ComentarioDto c: ComentarioCrud.obtenerPorVideo(videoMichael.getId())) {
			System.out.println(c);
		}
		
		for(ComentarioDto c: ComentarioCrud.obtenerPorPadre(primerComentario.getId())) {
			System.out.println(c);
		}
	}
}
