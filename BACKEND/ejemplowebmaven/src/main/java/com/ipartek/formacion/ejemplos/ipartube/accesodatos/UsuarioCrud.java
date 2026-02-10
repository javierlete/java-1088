package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import static com.ipartek.formacion.ejemplos.ipartube.accesodatos.Global.*;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioCrud {
	public static List<Usuario> obtenerTodos() {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

			t.commit();

			return usuarios;
		}
	}

	public static Usuario obtenerPorEmail(String email) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			Usuario usuario = em.createQuery("from Usuario u where u.email = :email", Usuario.class)
					.setParameter("email", email).getSingleResultOrNull();

			t.commit();

			return usuario;
		}
	}
	
	public static Usuario insertar(Usuario usuario) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.persist(usuario);

			t.commit();

			return usuario;
		}
	}
	
	public static List<Usuario> obtenerPorRol(String nombreRol) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

//			List<Usuario> usuarios = em.createQuery("from Usuario u where u.rol.nombre = :nombreRol", Usuario.class)
//					.setParameter("nombreRol", nombreRol).getResultList();

			List<Usuario> usuarios = em
					.createQuery("from Usuario u join fetch u.rol r where r.nombre = :nombreRol", Usuario.class)
					.setParameter("nombreRol", nombreRol).getResultList();

			t.commit();

			return usuarios;
		}
	}
}
