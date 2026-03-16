package com.ipartek.formacion.ejemplos.iparfood.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;

@RepositoryRestResource(path = "platos", collectionResourceRel = "platos")
public interface PlatoRepository extends JpaRepository<Plato, Long> {

}
