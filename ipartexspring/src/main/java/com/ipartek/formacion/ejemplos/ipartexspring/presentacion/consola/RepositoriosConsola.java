package com.ipartek.formacion.ejemplos.ipartexspring.presentacion.consola;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.UsuarioRepository;

@Component
public class RepositoriosConsola implements CommandLineRunner {

	// Spring en su arranque busca todos los interfaces de Repository que has creado

	// Crea una clase que los implementa basada en otra clase que tiene las
	// operaciones básicas
	// (Como nuestro DaoMensajeJpa y nuestro DaoJpa respectivamente)

	// La fábrica de Spring (como nuestra Fabrica) genera automáticamente un objeto
	// de la clase anterior en Singleton

	// Cuando ponemos @Autowired, se busca dentro de la fábrica un
	// objeto del tipo MensajeRepository
	// Encuentra el objeto Singleton anterior y se lo asigna a la variable
	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		Usuario javier = Usuario.builder().nombre("Javier").email("javier@email.net")
				.password(passwordEncoder.encode("javier")).build();
		Usuario pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("{noop}pepe").build();

		usuarioRepository.saveAll(List.of(javier, pepe));

		for (int i = 1; i < 50; i++) {
			mensajeRepository.save(Mensaje.builder().texto("Mensaje " + i)
					.fechaHora(LocalDateTime.now().minusDays(50 - i)).usuario(i % 2 == 0 ? javier : pepe).build());
		}

		for (Mensaje mensaje : mensajeRepository.findAll()) {
			System.out.println(mensaje);
		}

		System.out.println(usuarioRepository.findByEmail("pepe@email.net"));

		Page<Mensaje> mensajes;
		Pageable pageable = PageRequest.of(0, 10, Sort.by(new Sort.Order(Sort.Direction.DESC, "fechaHora"))); // Pageable.ofSize(10).withPage(0);

		do {
			System.out.println("Página " + (pageable.getPageNumber() + 1));

			mensajes = mensajeRepository.findAll(pageable);

			for (Mensaje mensaje : mensajes) {
				System.out.println(mensaje);
			}

			pageable = pageable.next();

		} while (mensajes.hasNext());
	}

}
