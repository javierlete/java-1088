package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRol;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRolJpa;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolPruebas {
	private static final DaoRol DAO = new DaoRolJpa();
	
	public static void main(String[] args) {
		DAO.insertar(new Rol(null, "ADMIN", "Administradores de la aplicación"));
		DAO.insertar(new Rol(null, "USER", "Usuarios de la aplicación"));
		DAO.insertar(new Rol(null, "OTROS", "Administradores de la aplicación"));

		listado();

		DAO.modificar(new Rol(3L, "NUEVOS", "Nuevos usuarios"));

		System.out.println(DAO.obtenerPorId(3L));

		DAO.borrar(3L);

		listado();

		String nombre = "USER";
		
		Optional<Rol> rol = DAO.obtenerPorNombre(nombre);
		
		System.out.println(rol);
	}

	private static void listado() {
		Iterable<Rol> roles = DAO.obtenerTodos();

		for (Rol rol : roles) {
			System.out.println(rol);
		}
	}
}
