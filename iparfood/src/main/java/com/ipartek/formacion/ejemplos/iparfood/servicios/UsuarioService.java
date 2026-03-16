package com.ipartek.formacion.ejemplos.iparfood.servicios;

import java.util.Collection;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UsuarioService {
	Pedido anadirPlatoAPedido(@Valid Plato plato);
	Pedido confirmarPedido(@Valid Pedido pedido);
	Collection<Pedido> listarPedidos(@NotNull Long idUsuario);
}
