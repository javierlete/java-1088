package com.ipartek.formacion.ejemplos.ipartexspring.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

public interface UsuarioService {
	Mensaje enviarMensaje(Mensaje mensaje);
	Optional<Usuario> buscarPorEmail(String email);
}
