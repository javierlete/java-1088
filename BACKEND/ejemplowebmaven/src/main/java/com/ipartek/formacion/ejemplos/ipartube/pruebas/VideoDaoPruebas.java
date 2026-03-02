package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRol;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class VideoDaoPruebas {
	private static final DaoRol DAO_ROL = (DaoRol) Fabrica.getObjeto("dao.rol");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.getObjeto("dao.usuario");
	private static final DaoVideo DAO_VIDEO = (DaoVideo) Fabrica.getObjeto("dao.video");
	private static final DaoComentario DAO_COMENTARIO= (DaoComentario) Fabrica.getObjeto("dao.comentario");
	
	public static void main(String[] args) {
		System.out.println("VIDEO DAO PRUEBAS");
		
		Rol rol = new Rol(null, "USUARIO", "Usuarios");
		DAO_ROL.insertar(rol);

		Usuario javier = new Usuario(null, null, "Javier", "javier@email.net", "javier", rol);
		DAO_USUARIO.insertar(javier);

		Usuario pepe = new Usuario(null, null, "Pepe", "pepe@email.net", "pepe", rol);
		DAO_USUARIO.insertar(pepe);

		Video videoMichael = new Video(null, "Michael Jackson en Auckly",
				"Con efectos 3D de la época.\r\nComo se nota la diferencia...",
				"https://i.ytimg.com/vi/ChrLRauOR28/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/ChrLRauOR28", javier);
		DAO_VIDEO.insertar(videoMichael);

		Video videoClasicosRock = new Video(null, "Clásicos del Rock", null,
				"https://i.ytimg.com/vi/4_O-y3SM3UM/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/4_O-y3SM3UM", pepe);
		DAO_VIDEO.insertar(videoClasicosRock);

		Video video8bbb = new Video(null, "8BBB en directo", "Video de la 8 Bit Bit Band en diciembre de 2025",
				"https://i.ytimg.com/vi/mzjqdhWxg8M/hq720.jpg", LocalDateTime.now(),
				"https://www.youtube.com/embed/mzjqdhWxg8M", javier);
		DAO_VIDEO.insertar(video8bbb);

		for (Video v : DAO_VIDEO.obtenerTodos()) {
			System.out.println(v);
		}

		Comentario primerComentario = new Comentario(null, "¡Primer comentario!", javier, videoMichael,
				LocalDateTime.now(), null);
		DAO_COMENTARIO.insertar(primerComentario);

		DAO_COMENTARIO.insertar(new Comentario(null, "Yo voy segundo", pepe, videoMichael, LocalDateTime.now(), null));
		DAO_COMENTARIO.insertar(new Comentario(null, "¡¡Hombre, como eres el que ha creado el video...!!", pepe,
				videoMichael, LocalDateTime.now(), primerComentario));

		for (ComentarioDto c : DAO_COMENTARIO.obtenerPorVideoDto(videoMichael.getId())) {
			System.out.println(c);
		}

		for (ComentarioDto c : DAO_COMENTARIO.obtenerPorPadreDto(primerComentario.getId())) {
			System.out.println(c);
		}
	}
}
