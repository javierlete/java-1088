package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class RolCrud {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.modelos");

	public static List<Rol> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		List<Rol> roles = em.createQuery("from Rol", Rol.class).getResultList();

		t.commit();

		return roles;
	}

	public static Rol obtenerPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Rol rol = em.find(Rol.class, id);

		t.commit();

		return rol;
	}

	public static Rol obtenerPorNombre(String nombre) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Rol rol = em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
				.setParameter("nombre", nombre).getSingleResultOrNull();

		t.commit();

		return rol;
	}

	public static Rol insertar(Rol rol) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(rol);

		t.commit();

		return rol;
	}

	public static Rol modificar(Rol rol) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.merge(rol);

		t.commit();

		return rol;
	}

	public static void borrar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.remove(em.find(Rol.class, id));

		t.commit();
	}
}
