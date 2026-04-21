package com.ipartek.formacion.ejemplos.iparfood.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor

@Slf4j

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidosRestController {
	private final UsuarioService usuarioService;

	@GetMapping
	public Iterable<Pedido> get(Long idUsuario) {
		return usuarioService.listarPedidos(idUsuario);
	}

	@PostMapping
	public Pedido post(@RequestBody PedidoDto pedidoDto) {
		log.info(pedidoDto.toString());

		var usuario = usuarioService.buscarPorEmail(pedidoDto.usuario().getEmail());

		var pedido = Pedido.builder().fechaHora(LocalDateTime.now()).usuario(usuario.get()).platos(pedidoDto.platos())
				.build();

		return usuarioService.confirmarPedido(pedido);
	}
}
