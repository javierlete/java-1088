package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PlatoRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final PedidoRepository pedidoRepository;
	private final PlatoRepository platoRepository;
	
	@Override
	public Pedido anadirPlatoAPedido(@Valid Plato plato) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	@Override
	public Pedido confirmarPedido(@Valid Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Collection<Pedido> listarPedidos(@NotNull Long idUsuario) {
		return pedidoRepository.findByUsuarioId(idUsuario);
	}

	@Override
	public Collection<Plato> listarPlatos() {
		return platoRepository.findAll();
	}

}
