package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import static com.ipartek.formacion.ejemplos.ipartube.daos.jpa.Configuracion.*;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolJpa implements DaoRol {

	public Iterable<Rol> obtenerTodos() {
		return DAO.ejecutarJpa(em -> em.createQuery("from Rol", Rol.class).getResultList());
	}

	public Iterable<Rol> obtenerTodosConUsuarios() {
		return DAO
				.ejecutarJpa(em -> em.createQuery("from Rol r left join fetch r.usuarios", Rol.class).getResultList());
	}

	public Optional<Rol> obtenerPorId(Long id) {
		return Optional.ofNullable(DAO.ejecutarJpa(em -> em.find(Rol.class, id)));
	}

	public Optional<Rol> obtenerPorNombre(String nombre) {
		return Optional
				.ofNullable(DAO.ejecutarJpa(em -> em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
						.setParameter("nombre", nombre).getSingleResultOrNull()));
	}

	public Rol insertar(Rol rol) {
		return DAO.ejecutarJpa(em -> {
			em.persist(rol);
			return rol;
		});
	}

	public Rol modificar(Rol rol) {
		return DAO.ejecutarJpa(em -> {
			em.merge(rol);
			return rol;
		});
	}

	public void borrar(Long id) {
		DAO.ejecutarJpa(em -> {
			em.remove(em.find(Rol.class, id));
			return null;
		});

	}
}
