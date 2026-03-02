package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {
	public DaoUsuarioJpa() {
		super(Usuario.class, "com.ipartek.formacion.ejemplos.ipartube.modelos");
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return ejecutarJpa(em -> em.createQuery("from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull());
	}

	@Override
	public Iterable<Usuario> obtenerPorRol(String nombreRol) {
		return ejecutarJpa(
				em -> em.createQuery("from Usuario u join fetch u.rol r where r.nombre = :nombreRol", Usuario.class)
						.setParameter("nombreRol", nombreRol).getResultList());
	}
}
