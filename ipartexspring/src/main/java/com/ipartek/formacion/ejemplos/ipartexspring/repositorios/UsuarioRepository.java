package com.ipartek.formacion.ejemplos.ipartexspring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

@RepositoryRestResource(path = "usuarios", collectionResourceRel = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
