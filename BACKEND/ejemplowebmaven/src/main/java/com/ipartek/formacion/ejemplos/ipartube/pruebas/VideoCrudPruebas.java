package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.RolCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
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

		for (int i = 1; i <= 5; i++) {
			Video video = new Video(null, "Prueba " + i, "Descripción " + i, "https://picsum.photos/400/300?" + i,
					LocalDateTime.now(), "https://www.youtube.com/" + i, i % 2 == 0 ? javier : pepe);
			VideoCrud.insertar(video);
		}

		for (Video v : VideoCrud.obtenerTodos()) {
			System.out.println(v);
		}
	}
}
