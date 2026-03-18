package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PlatoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.ServicioException;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Validated
@Slf4j

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final PedidoRepository pedidoRepository;
	private final PlatoRepository platoRepository;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public Pedido anadirPlatoAPedido(@NotNull Long id, Pedido pedido) {
		var plato = platoRepository.findById(id);
		
		if(plato.isEmpty()) {
			throw new ServicioException("No se ha encontrado el plato a añadir");
		}
		
		return anadirPlatoAPedido(plato.get(), pedido);
	}

	@Override
	public Pedido anadirPlatoAPedido(@Valid Plato plato, Pedido pedido) {
		pedido.getPlatos().add(plato);
		
		return pedido;
	}

	@Override
	public Pedido confirmarPedido(@Valid Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Collection<Pedido> listarPedidos(@NotNull Long idUsuario) {
		return pedidoRepository.findByUsuarioIdOrderByFechaHoraDesc(idUsuario);
	}

	@Override
	public Collection<Plato> listarPlatos() {
		return platoRepository.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
