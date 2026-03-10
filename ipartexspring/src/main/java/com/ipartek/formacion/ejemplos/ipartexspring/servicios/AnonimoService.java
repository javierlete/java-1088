package com.ipartek.formacion.ejemplos.ipartexspring.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

import jakarta.validation.Valid;

public interface AnonimoService {
	Iterable<Mensaje> listarMensajes();

	Usuario registrar(@Valid Usuario usuario);
	Optional<Usuario> autenticar(String email, String password);
}
