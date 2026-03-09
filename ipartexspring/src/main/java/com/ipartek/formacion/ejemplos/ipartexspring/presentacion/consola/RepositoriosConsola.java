package com.ipartek.formacion.ejemplos.ipartexspring.presentacion.consola;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.UsuarioRepository;

@Component
public class RepositoriosConsola implements CommandLineRunner {
	// Spring en su arranque busca todos los interfaces de Repository que has creado
	
	// Crea una clase que los implementa basada en otra clase que tiene las operaciones básicas
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
	
	@Override
	public void run(String... args) throws Exception {
		Usuario javier = new Usuario(null, "Javier", "javier@email.net", "javier");
		Usuario pepe = new Usuario(null, "Pepe", "pepe@email.net", "pepe");
		
		Mensaje mensaje1 = new Mensaje(null, "Primer mensaje", LocalDateTime.now(), javier); 
		Mensaje mensaje2 = new Mensaje(null, "Segundo mensaje", LocalDateTime.now(), pepe);
		
		usuarioRepository.saveAll(List.of(javier, pepe));
		
		mensajeRepository.save(mensaje1);
		mensajeRepository.save(mensaje2);
		
		for(Mensaje mensaje: mensajeRepository.findAll()) {
			System.out.println(mensaje);
		}
		
		System.out.println(usuarioRepository.findByEmail("pepe@email.net"));
	}

}
