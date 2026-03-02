package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import static com.ipartek.formacion.ejemplos.ipartube.daos.jpa.Configuracion.*;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class DaoUsuarioJpa implements DaoUsuario {

	@Override
	public Usuario obtenerPorEmail(String email) {
		return DAO.ejecutarJpa(em -> em.createQuery("from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull());
	}

	@Override
	public Iterable<Usuario> obtenerPorRol(String nombreRol) {
		return DAO.ejecutarJpa(
				em -> em.createQuery("from Usuario u join fetch u.rol r where r.nombre = :nombreRol", Usuario.class)
						.setParameter("nombreRol", nombreRol).getResultList());
	}

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return DAO.ejecutarJpa(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return DAO.ejecutarJpa(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

}
