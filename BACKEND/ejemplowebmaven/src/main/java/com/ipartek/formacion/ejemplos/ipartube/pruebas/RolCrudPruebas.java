package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.RolCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class RolCrudPruebas {
	public static void main(String[] args) {
		RolCrud.insertar(new Rol(null, "ADMIN", "Administradores de la aplicación"));
		RolCrud.insertar(new Rol(null, "USER", "Usuarios de la aplicación"));
		RolCrud.insertar(new Rol(null, "OTROS", "Administradores de la aplicación"));

		listado();

		RolCrud.modificar(new Rol(3L, "NUEVOS", "Nuevos usuarios"));

		System.out.println(RolCrud.obtenerPorId(3L));

		RolCrud.borrar(3L);

		listado();

		String nombre = "USER";
		
		Rol rol = RolCrud.obtenerPorNombre(nombre);
		
		System.out.println(rol);
	}

	private static void listado() {
		List<Rol> roles = RolCrud.obtenerTodos();

		for (Rol rol : roles) {
			System.out.println(rol);
		}
	}
}
