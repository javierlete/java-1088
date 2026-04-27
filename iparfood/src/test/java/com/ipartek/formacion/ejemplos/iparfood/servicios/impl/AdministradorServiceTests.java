package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.TipoComidaRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.iparfood.servicios.ServicioException;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AdministradorServiceTests {

	private static final TipoComida ITALIANA = TipoComida.builder().nombre("Italiana").build();
	private static final TipoComida ASIATICA = TipoComida.builder().nombre("Asiática").build();
	private static final TipoComida AMERICANA = TipoComida.builder().nombre("Americana").build();

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private TipoComidaRepository tipoComidaRepository;

	@BeforeAll
	void setUpBeforeClass() {
		tipoComidaRepository.saveAll(List.of(AMERICANA, ASIATICA, ITALIANA));
	}

	@Test
	void testListarPedidos() {
		fail("Not yet implemented");
	}

	@Test
	void testListarPlatos() {
		fail("Not yet implemented");
	}

	@Test
	void testCrearPlatoCorrecto() {
		var burger = administradorService.crearPlato(
				Plato.builder().nombre("Burger").precio(new BigDecimal("8.12")).tipoComida(AMERICANA).build());

		assertNotNull(burger);

		assertEquals(
				Plato.builder().id(1L).nombre("Burger").precio(new BigDecimal("8.12")).tipoComida(AMERICANA).build(),
				burger);
	}

	@Test
	void testCrearPlatoNoValidado() {
		var plato = Plato.builder().nombre("").precio(new BigDecimal("-5")).tipoComida(AMERICANA).build();

		assertThrows(ConstraintViolationException.class, () -> administradorService.crearPlato(plato));
	}

	@Test
	void testCrearPlatoConId() {
		var plato = Plato.builder().id(5L).nombre("asdf").precio(new BigDecimal("5")).tipoComida(AMERICANA).build();
		
		assertThrows(ServicioException.class, () -> administradorService.crearPlato(plato));
	}

	@Test
	void testModificarPlato() {
		fail("Not yet implemented");
	}

	@Test
	void testBorrarPlato() {
		fail("Not yet implemented");
	}

	@Test
	void testListarTiposComida() {
		fail("Not yet implemented");
	}

	@Test
	void testObtenerPlatoPorId() {
		fail("Not yet implemented");
	}

	@Test
	void testAdministradorServiceImpl() {
		fail("Not yet implemented");
	}

}
