package com.ipartek.formacion.ejemplos.iparfood.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;

@RepositoryRestResource(path = "tiposComida", collectionResourceRel = "tiposComida")
public interface TipoComidaRepository extends JpaRepository<TipoComida, Long> {

}
