package com.ipartek.formacion.ejemplos.iparfood.servicios;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UsuarioService {
	Pedido anadirPlatoAPedido(@NotNull Long id, Pedido pedido);
	Pedido anadirPlatoAPedido(@Valid Plato plato, Pedido pedido);
	Pedido confirmarPedido(@Valid Pedido pedido);
	Collection<Pedido> listarPedidos(@NotNull Long idUsuario);
	Collection<Plato> listarPlatos();
	Optional<Usuario> buscarPorEmail(String username);
}
