package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	@Query("from Mensaje m order by m.fechaHora desc")
	Iterable<Mensaje> obtenerTodos();
}
