package com.ipartek.formacion.ejemplos.iparfood.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PlatoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.TipoComidaRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PedidosRestControllerTest {
	@Autowired
	private PedidosRestController pedidosRestController;

	@Autowired
	private TipoComidaRepository tipoComidaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PlatoRepository platoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private AdministradorService administradorService;

	private Usuario javier;
	
	private Plato burger;
	private Plato rollitos;
	@SuppressWarnings("unused")
	private Plato ensalada;

	@BeforeAll
	void setUpBeforeClass() {
		var americana = TipoComida.builder().nombre("Americana").build();
		var asiatica = TipoComida.builder().nombre("Asiática").build();
		var italiana = TipoComida.builder().nombre("Italiana").build();

		tipoComidaRepository.saveAll(List.of(americana, asiatica, italiana));

		burger = administradorService.crearPlato(
				Plato.builder().nombre("Burger").precio(new BigDecimal("8.12")).tipoComida(americana).build());
		rollitos = administradorService.crearPlato(
				Plato.builder().nombre("Rollitos").precio(new BigDecimal("5.12")).tipoComida(asiatica).build());
		ensalada = administradorService.crearPlato(
				Plato.builder().nombre("Ensalada China").precio(new BigDecimal("7.12")).tipoComida(asiatica).build());

		javier = usuarioRepository.save(Usuario.builder().nombre("Javier").direccion("Mi casa")
				.email("javier@email.net").password("{noop}javier").rol("ADMINISTRADOR").build());
	}
	
	@AfterAll
	void tearDownAfterClass() {
		pedidoRepository.deleteAll();
		platoRepository.deleteAll();
		tipoComidaRepository.deleteAll();
		usuarioRepository.deleteAll();
	}

	@Test
	void testPost() {
		var pedido = pedidosRestController.post(new PedidoDto(Usuario.builder().email("javier@email.net").build(),
				List.of(Plato.builder().id(burger.getId()).build(), Plato.builder().id(rollitos.getId()).build())));

		assertNotNull(pedido);

		System.out.println(pedido);
		
		assertEquals(javier.getNombre(), pedido.getUsuario().getNombre());
		
		assertEquals(2, pedido.getPlatos().size());
		
		assertTrue(pedido.getPlatos().stream().anyMatch(plato -> plato.getId().equals(burger.getId())));
		assertTrue(pedido.getPlatos().stream().anyMatch(plato -> plato.getId().equals(rollitos.getId())));
	}

}
