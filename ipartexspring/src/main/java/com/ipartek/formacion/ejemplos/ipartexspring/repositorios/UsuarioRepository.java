package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
