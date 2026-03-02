package com.ipartek.formacion.ejemplos.ipartube.daos;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.Dao;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
	Iterable<Usuario> obtenerPorRol(String nombreRol);
}
