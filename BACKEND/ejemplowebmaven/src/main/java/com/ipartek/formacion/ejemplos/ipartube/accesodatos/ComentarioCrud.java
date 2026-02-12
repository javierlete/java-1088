package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import static com.ipartek.formacion.ejemplos.ipartube.accesodatos.Global.*;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ComentarioCrud {

	private static final String COMENTARIO_DTO = """
			select new com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto(
				c.id, c.texto, c.fecha, c.usuario.id, c.usuario.nombre
			) from Comentario c
			""";

	public static List<ComentarioDto> obtenerPorVideo(Long idVideo) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<ComentarioDto> comentarios = em
					.createQuery(COMENTARIO_DTO + "where c.video.id = :id and c.comentarioPadre is null",
							ComentarioDto.class)
					.setParameter("id", idVideo).getResultList();

			t.commit();

			return comentarios;
		}
	}

	public static ComentarioDto obtenerPorId(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			ComentarioDto comentario = em.createQuery(COMENTARIO_DTO + "where c.id = :id", ComentarioDto.class)
					.setParameter("id", id).getSingleResultOrNull();

			t.commit();

			return comentario;
		}
	}

	public static List<ComentarioDto> obtenerPorPadre(Long idComentario) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<ComentarioDto> comentarios = em
					.createQuery(COMENTARIO_DTO + "where c.comentarioPadre.id = :id", ComentarioDto.class)
					.setParameter("id", idComentario).getResultList();

			t.commit();

			return comentarios;
		}
	}

	public static void insertar(Comentario comentario) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.persist(comentario);

			t.commit();
		}
	}
}
