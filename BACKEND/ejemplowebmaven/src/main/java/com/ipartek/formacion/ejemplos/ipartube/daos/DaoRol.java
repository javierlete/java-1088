package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.Dao;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public interface DaoRol extends Dao<Rol> {
	Iterable<Rol> obtenerTodosConUsuarios();
	Optional<Rol> obtenerPorNombre(String nombre);
}
