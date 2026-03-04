package com.ipartek.formacion.ejemplos.ipartex.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

public interface AnonimoNegocio {
	Optional<Usuario> autenticar(String email, String password);
	Iterable<Mensaje> listarMensajes();
}
