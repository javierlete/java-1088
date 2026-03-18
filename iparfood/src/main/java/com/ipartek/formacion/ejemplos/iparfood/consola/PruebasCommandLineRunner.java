package com.ipartek.formacion.ejemplos.iparfood.consola;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.TipoComidaRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor

@Slf4j

@Component
public class PruebasCommandLineRunner implements CommandLineRunner {
	private final AdministradorService administradorService;
	private final UsuarioService usuarioService;
	private final TipoComidaRepository tipoComidaRepository;
	private final UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		var americana = TipoComida.builder().nombre("Americana").build();
		var asiatica = TipoComida.builder().nombre("Asiática").build();
		var italiana = TipoComida.builder().nombre("Italiana").build();

		tipoComidaRepository.saveAll(List.of(americana, asiatica, italiana));

		var burger = administradorService.crearPlato(
				Plato.builder().nombre("Burger").precio(new BigDecimal("8.12")).tipoComida(americana).build());
		var pizza = administradorService.crearPlato(
				Plato.builder().nombre("Pizza").precio(new BigDecimal("12.12")).tipoComida(italiana).build());
		var rollitos = administradorService.crearPlato(
				Plato.builder().nombre("Rollitos").precio(new BigDecimal("5.12")).tipoComida(asiatica).build());
		administradorService.crearPlato(
				Plato.builder().nombre("Ensalada China").precio(new BigDecimal("7.12")).tipoComida(asiatica).build());

		var javier = usuarioRepository.save(Usuario.builder().nombre("Javier").direccion("Mi casa")
				.email("javier@email.net").password("{noop}javier").rol("ADMINISTRADOR").build());
		var pepe = usuarioRepository.save(Usuario.builder().nombre("Pepe").direccion("Su casa")
				.email("pepe@email.net").password("{noop}pepe").build());

		var pedidoJavier = usuarioService.confirmarPedido(Pedido.builder().fechaHora(LocalDateTime.now())
				.usuario(javier).platos(List.of(burger, rollitos, pizza)).build());
		var pedidoPepe = usuarioService.confirmarPedido(
				Pedido.builder().fechaHora(LocalDateTime.now()).usuario(pepe).platos(List.of(burger, pizza)).build());

		log.debug(pedidoJavier.toString());
		log.debug(pedidoPepe.toString());
	}

}
