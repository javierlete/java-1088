package com.ipartek.formacion.ejemplos.ipartex.accesodatos.jpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class, "com.ipartek.formacion.ejemplos.ipartex.entidades");
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return ejecutarJpa(
				em -> Optional.ofNullable(em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}

}
