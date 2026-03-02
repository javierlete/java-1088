package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import static com.ipartek.formacion.ejemplos.ipartube.daos.jpa.Configuracion.*;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;

public class DaoComentarioJpa implements DaoComentario {

	private static final String COMENTARIO_DTO_PREFIJO_JPQL = """
			select new com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto(
				c.id, c.texto, c.fecha, c.usuario.id, c.usuario.nombre, count(r)
			) from Comentario c
			left join c.respuestas r
			""";
	private static final String COMENTARIO_DTO_SUFIJO_JPQL = " group by c.id, c.texto, c.fecha, c.usuario.id, c.usuario.nombre ";

	@Override
	public Iterable<ComentarioDto> obtenerPorVideoDto(Long idVideo) {
		return DAO.ejecutarJpa(em -> em
				.createQuery(COMENTARIO_DTO_PREFIJO_JPQL + "where c.video.id = :id and c.comentarioPadre is null"
						+ COMENTARIO_DTO_SUFIJO_JPQL, ComentarioDto.class)
				.setParameter("id", idVideo).getResultList());
	}

	@Override
	public Iterable<ComentarioDto> obtenerPorPadreDto(Long idComentario) {
		return DAO
				.ejecutarJpa(em -> em
						.createQuery(COMENTARIO_DTO_PREFIJO_JPQL + "where c.comentarioPadre.id = :id"
								+ COMENTARIO_DTO_SUFIJO_JPQL, ComentarioDto.class)
						.setParameter("id", idComentario).getResultList());
	}

	@Override
	public Optional<ComentarioDto> obtenerPorIdDto(Long id) {
		return DAO
				.ejecutarJpa(
						em -> Optional
								.ofNullable(em
										.createQuery(COMENTARIO_DTO_PREFIJO_JPQL + "where c.id = :id"
												+ COMENTARIO_DTO_SUFIJO_JPQL, ComentarioDto.class)
										.setParameter("id", id).getSingleResultOrNull()));
	}

	@Override
	public Comentario insertar(Comentario comentario) {
		return DAO.ejecutarJpa(em -> {
			em.persist(comentario);
			return comentario;
		});
	}

}
