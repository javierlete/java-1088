package com.ipartek.formacion.ejemplos.bibliotecas.dao;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	private final EntityManagerFactory entityManagerFactory;

	public DaoJpa(String unidadPersistencia) {
		entityManagerFactory = Persistence.createEntityManagerFactory(unidadPersistencia);
	}

	public <R> R ejecutarJpa(Function<EntityManager, R> funcion) {
		try (EntityManager em = entityManagerFactory.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			R resultado = funcion.apply(em);
			
			t.commit();

			return resultado;
		} catch(Exception e) {
			throw new DaoException("Error en la operación de JPA", e);
		}
	}
	
}
