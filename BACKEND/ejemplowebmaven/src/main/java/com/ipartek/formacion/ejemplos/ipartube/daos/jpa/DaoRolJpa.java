package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoRol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

public class DaoRolJpa extends DaoJpa<Rol> implements DaoRol {
	public DaoRolJpa() {
		super(Rol.class, "com.ipartek.formacion.ejemplos.ipartube.modelos");
	}

	@Override
	public Iterable<Rol> obtenerTodosConUsuarios() {
		return ejecutarJpa(em -> em.createQuery("from Rol r left join fetch r.usuarios", Rol.class).getResultList());
	}

	@Override
	public Optional<Rol> obtenerPorNombre(String nombre) {
		return Optional.ofNullable(ejecutarJpa(em -> em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
				.setParameter("nombre", nombre).getSingleResultOrNull()));
	}

}
