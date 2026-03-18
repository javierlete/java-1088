package com.ipartek.formacion.ejemplos.iparfood.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;

import jakarta.validation.constraints.NotNull;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Collection<Pedido> findByUsuarioId(Long idUsuario);

	Collection<Pedido> findByUsuarioIdOrderByFechaHoraDesc(@NotNull Long idUsuario);

}
