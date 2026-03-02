package com.ipartek.formacion.ejemplos.ipartube.daos.treemap;

import java.util.Optional;
import java.util.TreeMap;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolTreeMap implements DaoRol {
	private static final TreeMap<Long, Rol> roles = new TreeMap<>();
	
	@Override
	public Iterable<Rol> obtenerTodos() {
		return roles.values();
	}

	@Override
	public Optional<Rol> obtenerPorId(Long id) {
		return Optional.ofNullable(roles.get(id));
	}

	@Override
	public Rol insertar(Rol objeto) {
		Long id = roles.size() > 0 ? roles.lastKey() + 1L : 1L;
		objeto.setId(id);
		
		return roles.put(objeto.getId(), objeto);
	}

	@Override
	public Rol modificar(Rol objeto) {
		return roles.put(objeto.getId(), objeto);
	}

	@Override
	public void borrar(Long id) {
		roles.remove(id);
	}

	@Override
	public Iterable<Rol> obtenerTodosConUsuarios() {
		return roles.values();
	}

	@Override
	public Optional<Rol> obtenerPorNombre(String nombre) {
		return roles.values().stream().filter(rol -> rol.getNombre().equals(nombre)).findFirst();
	}

}
