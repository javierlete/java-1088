package com.ipartek.formacion.ejemplos.ipartex.logicanegocio.impl;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private final DaoMensaje daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje"); 
	private final DaoUsuario daoUsuario = (DaoUsuario) Fabrica.getObjeto("dao.usuario"); 
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = daoUsuario.obtenerPorEmail(email);
		
		if(usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			return usuario;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Iterable<Mensaje> listarMensajes() {
		return daoMensaje.obtenerTodos();
	}

}
