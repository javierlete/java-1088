package com.ipartek.formacion.ejemplos.iparfood.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
