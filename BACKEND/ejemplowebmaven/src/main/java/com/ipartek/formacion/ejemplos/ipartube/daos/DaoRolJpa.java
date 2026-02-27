package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoFabricaJpa;
import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolJpa implements DaoRol {

	private static final DaoJpa dao = DaoFabricaJpa.getDaoJpa("daojpa.rol");

	public Iterable<Rol> obtenerTodos() {
		return dao.ejecutarJpa(em -> em.createQuery("from Rol", Rol.class).getResultList());
	}

	public Iterable<Rol> obtenerTodosConUsuarios() {
		return dao
				.ejecutarJpa(em -> em.createQuery("from Rol r left join fetch r.usuarios", Rol.class).getResultList());
	}

	public Optional<Rol> obtenerPorId(Long id) {
		return Optional.ofNullable(dao.ejecutarJpa(em -> em.find(Rol.class, id)));
	}

	public Optional<Rol> obtenerPorNombre(String nombre) {
		return Optional
				.ofNullable(dao.ejecutarJpa(em -> em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
						.setParameter("nombre", nombre).getSingleResultOrNull()));
	}

	public Rol insertar(Rol rol) {
		return dao.ejecutarJpa(em -> {
			em.persist(rol);
			return rol;
		});
	}

	public Rol modificar(Rol rol) {
		return dao.ejecutarJpa(em -> {
			em.merge(rol);
			return rol;
		});
	}

	public void borrar(Long id) {
		dao.ejecutarJpa(em -> {
			em.remove(em.find(Rol.class, id));
			return null;
		});

	}
}
