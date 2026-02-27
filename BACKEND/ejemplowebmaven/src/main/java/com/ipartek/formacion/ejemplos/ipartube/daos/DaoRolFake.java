package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.List;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolFake implements DaoRol {

	@Override
	public Iterable<Rol> obtenerTodos() {
		return List.of(new Rol(1L, "DEMO", "Demostración"), new Rol(2L, "DEMO2", "Demostración2"));
	}

	@Override
	public Optional<Rol> obtenerPorId(Long id) {
		return Optional.empty();
	}

	@Override
	public Rol insertar(Rol objeto) {
		return null;
	}

	@Override
	public Rol modificar(Rol objeto) {
		return null;
	}

	@Override
	public void borrar(Long id) {
		
	}

	@Override
	public Iterable<Rol> obtenerTodosConUsuarios() {
		return List.of(new Rol(1L, "DEMO", "Demostración"), new Rol(2L, "DEMO2", "Demostración2"));
	}

	@Override
	public Optional<Rol> obtenerPorNombre(String nombre) {
		return Optional.of(new Rol(1L, nombre, "Demostración " + nombre));
	}

}
