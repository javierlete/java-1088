package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.List;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolNull implements DaoRol {

	@Override
	public Iterable<Rol> obtenerTodos() {
		return List.of();
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
		return List.of();
	}

	@Override
	public Optional<Rol> obtenerPorNombre(String nombre) {
		return Optional.empty();
	}

}
