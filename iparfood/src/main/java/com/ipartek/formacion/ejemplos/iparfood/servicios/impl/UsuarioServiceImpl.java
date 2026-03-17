package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PlatoRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.ServicioException;
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
	private final PedidoDto pedidoSesion;
	
	@Override
	public Pedido anadirPlatoAPedido(@NotNull Long id) {
		System.out.println("USUARIO SERVICE " + id);
		System.out.println("USUARIO SERVICE " + pedidoSesion);
		
		var plato = platoRepository.findById(id);
		
		if(plato.isEmpty()) {
			throw new ServicioException("No se ha encontrado el plato a añadir");
		}
		
		return anadirPlatoAPedido(plato.get());
	}

	@Override
	public Pedido anadirPlatoAPedido(@Valid Plato plato) {
		pedidoSesion.getPlatos().add(plato);
		
		return pedidoSesion;
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
