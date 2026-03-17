package com.ipartek.formacion.ejemplos.iparfood.consola;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class PruebasCommandLineRunner implements CommandLineRunner {
	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoComidaRepository tipoComidaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

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
		var ensalada = administradorService.crearPlato(
				Plato.builder().nombre("Ensalada China").precio(new BigDecimal("7.12")).tipoComida(asiatica).build());

		var javier = usuarioRepository.save(Usuario.builder().nombre("Javier").direccion("Mi casa")
				.email("javier@email.net").password("{noop}javier").build());

		var pedido = usuarioService.confirmarPedido(
				Pedido.builder().usuario(javier).platos(List.of(burger, rollitos, ensalada, pizza)).build());

		System.out.println(pedido);
	}

}
