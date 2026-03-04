package com.ipartek.formacion.ejemplos.ipartex.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.Dao;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmail(String email);
}
