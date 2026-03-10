package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;

@RepositoryRestResource(path = "mensajes", collectionResourceRel = "mensajes")
public interface MensajeRepository extends CrudRepository<Mensaje, Long>, PagingAndSortingRepository<Mensaje, Long> {

}
