package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class RolPruebas {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.modelos");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(new Rol(null, "ADMIN", "Administradores de la aplicación")); // insertar
		em.persist(new Rol(null, "USER", "Usuarios de la aplicación"));
		em.persist(new Rol(null, "OTROS", "Administradores de la aplicación"));

		listado(em);

		em.merge(new Rol(3L, "NUEVOS", "Nuevos usuarios")); // modificar

		System.out.println(em.find(Rol.class, 3L)); // obtenerPorId

		em.remove(em.find(Rol.class, 3L)); // borrar

		listado(em);

		String nombre = "USER";
		
		Rol rol = em.createQuery("from Rol r where r.nombre = :nombre", Rol.class)
				.setParameter("nombre", nombre).getSingleResultOrNull();
		
		System.out.println(rol);

		t.commit();
	}

	private static void listado(EntityManager em) {
		List<Rol> roles = em.createQuery("select r from Rol r", Rol.class).getResultList(); // obtenerTodos

		for (Rol rol : roles) {
			System.out.println(rol);
		}
	}
}
