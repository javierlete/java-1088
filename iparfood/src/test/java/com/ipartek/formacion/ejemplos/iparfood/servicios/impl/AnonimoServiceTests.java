package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AnonimoService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AnonimoServiceTests {

	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final String PEPE_EMAIL = "pepe@email.net";
	private static final String JAVIER_NOMBRE = "Javier";
	private static final String JAVIER_EMAIL = "javier@email.net";
	private static final String JAVIER_ROL = "ADMINISTRADOR";

	private static final Usuario USUARIO_INCORRECTO = Usuario.builder().email("noexiste@email.net").password("daigual")
			.build();
	private static final Usuario PASSWORD_INCORRECTO = Usuario.builder().email(PEPE_EMAIL).password("incorrecto")
			.build();
	private static final Usuario JAVIER_LOGIN = Usuario.builder().email(JAVIER_EMAIL).password("javier").build();

	private static final Usuario PEPE = Usuario.builder().nombre("Pepe").direccion("Su casa").email(PEPE_EMAIL)
			.password("{noop}pepe").build();
	private static final Usuario JAVIER = Usuario.builder().nombre(JAVIER_NOMBRE).direccion("Mi casa")
			.email(JAVIER_EMAIL).password("{noop}javier").rol(JAVIER_ROL).build();

	@BeforeAll
	void setUpBeforeClass() {
		usuarioRepository.save(JAVIER);
		usuarioRepository.save(PEPE);
	}

	@Test
	void testLoginCorrecto() {
		var usuario = JAVIER_LOGIN;

		var usuarioLogin = anonimoService.login(usuario);

		assertNotNull(usuarioLogin);

		assertEquals(JAVIER_ROL, usuarioLogin.getRol());
		assertEquals(JAVIER_NOMBRE, usuarioLogin.getNombre());
	}

	@Test
	void testLoginUsuarioIncorrecto() {
		var usuario = USUARIO_INCORRECTO;

		var usuarioLogin = anonimoService.login(usuario);

		assertNull(usuarioLogin);
	}

	@Test
	void testLoginPasswordIncorrecta() {
		var usuario = PASSWORD_INCORRECTO;

		var usuarioLogin = anonimoService.login(usuario);

		assertNull(usuarioLogin);
	}

}
