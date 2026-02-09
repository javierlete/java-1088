package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import static com.ipartek.formacion.ejemplos.ipartube.accesodatos.Global.*;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioCrud {
	public static List<Usuario> obtenerTodos() {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

		t.commit();

		return usuarios;
	}

	public static Usuario obtenerPorEmail(String email) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Usuario usuario = em.createQuery("from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull();

		t.commit();

		return usuario;
	}
	
	public static Usuario insertar(Usuario usuario) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(usuario);

		t.commit();

		return usuario;
	}
}
