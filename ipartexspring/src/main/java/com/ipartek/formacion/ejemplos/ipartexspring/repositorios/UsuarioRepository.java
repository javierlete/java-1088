package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
