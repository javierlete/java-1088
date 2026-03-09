package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;

public interface MensajeRepository extends CrudRepository<Mensaje, Long>, PagingAndSortingRepository<Mensaje, Long> {

}
