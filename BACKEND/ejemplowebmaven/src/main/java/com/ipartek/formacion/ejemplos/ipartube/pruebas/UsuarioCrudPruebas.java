package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.RolCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class UsuarioCrudPruebas {
	public static void main(String[] args) {
		Rol admin = new Rol(null, "ADMIN", null);
		Rol user = new Rol(null, "USER", null);

		RolCrud.insertar(admin);
		RolCrud.insertar(user);

		UsuarioCrud.insertar(new Usuario(null, null, "Javier", "javier@email.net", "javier", admin));
		UsuarioCrud.insertar(new Usuario(null, null, "Pepe", "pepe@email.net", "pepe", user));
		UsuarioCrud.insertar(new Usuario(null, null, "Juan", "juan@email.net", "juan", user));

		for (Usuario usuario : UsuarioCrud.obtenerTodos()) {
			System.out.println(usuario);
		}

		for (Usuario usuario : UsuarioCrud.obtenerPorRol("USER")) {
			System.out.println(usuario);
		}

		for (Rol rol : RolCrud.obtenerTodos()) {
			System.out.println(rol);

			for (Usuario usuario : UsuarioCrud.obtenerPorRol(rol.getNombre())) {
				System.out.println(usuario);
			}
		}

		System.out.println("LISTADO TOTAL DE ROLES CON USUARIOS");

		for (Rol r : RolCrud.obtenerTodosConUsuarios()) {
			System.out.println(r);

			for (Usuario usuario : r.getUsuarios()) {
				System.out.println(usuario);
			}
		}
	}
}
