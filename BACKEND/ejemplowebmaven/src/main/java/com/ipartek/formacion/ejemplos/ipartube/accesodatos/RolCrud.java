package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import static com.ipartek.formacion.ejemplos.ipartube.accesodatos.Global.*;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RolCrud {
	public static List<Rol> obtenerTodos() {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<Rol> roles = em.createQuery("from Rol", Rol.class).getResultList();

			t.commit();

			return roles;
		}
	}

	public static Rol obtenerPorId(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			Rol rol = em.find(Rol.class, id);

			t.commit();

			return rol;
		}
	}

	public static Rol obtenerPorNombre(String nombre) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			Rol rol = em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
					.setParameter("nombre", nombre).getSingleResultOrNull();

			t.commit();

			return rol;
		}
	}

	public static Rol insertar(Rol rol) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.persist(rol);

			t.commit();

			return rol;
		}
	}

	public static Rol modificar(Rol rol) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.merge(rol);

			t.commit();

			return rol;
		}
	}

	public static void borrar(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(em.find(Rol.class, id));

			t.commit();
		}
	}
}
