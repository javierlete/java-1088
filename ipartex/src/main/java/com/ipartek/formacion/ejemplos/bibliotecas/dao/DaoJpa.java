package com.ipartek.formacion.ejemplos.bibliotecas.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa<T> implements Dao<T> {
	private final EntityManagerFactory entityManagerFactory;
	private final static Map<String, EntityManagerFactory> emfs = new HashMap<>();
	
	private final Class<T> clase;

	public DaoJpa(Class<T> clase, String unidadPersistencia) {
		this.clase = clase;
		
		if(emfs.containsKey(unidadPersistencia)) {
			entityManagerFactory = emfs.get(unidadPersistencia);
			return;
		}
		
		entityManagerFactory = Persistence.createEntityManagerFactory(unidadPersistencia);
		
		emfs.put(unidadPersistencia, entityManagerFactory);
	}

	public <R> R ejecutarJpa(Function<EntityManager, R> funcion) {
		EntityManager em = entityManagerFactory.createEntityManager();
	    EntityTransaction t = null;
	    
	    try {
			t = em.getTransaction();

			t.begin();

			R resultado = funcion.apply(em);

			t.commit();

			return resultado;
		} catch (Exception e) {
			if (t != null && t.isActive()) {
				try {
	                t.rollback();
	            } catch (Exception ex) {
	                // rollback fallido, ignorar
	            }
			}

			throw new DaoException("Error en la operación de JPA", e);
		} finally {
	        if (em.isOpen()) {
	            em.close();
	        }
	    }
	}

	
	
	@Override
	public Iterable<T> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from " + clase.getSimpleName(), clase).getResultList());
	}

	@Override
	public Optional<T> obtenerPorId(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.find(clase, id)));
	}

	@Override
	public T insertar(T objeto) {
		return ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	@Override
	public T modificar(T objeto) {
		return ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(clase, id));
			return null;
		});
	}

}
