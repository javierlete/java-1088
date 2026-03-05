package com.ipartek.formacion.ejemplos.ipartex.logicanegocio.impl;

import java.util.Optional;
import java.util.Set;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoException;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.bibliotecas.logicanegocio.LogicaNegocioException;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private final DaoMensaje daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje"); 
	private final DaoUsuario daoUsuario = (DaoUsuario) Fabrica.getObjeto("dao.usuario"); 
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		return daoMensaje.obtenerTodos();
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		try {
			return daoUsuario.insertar(usuario);
		} catch (DaoException e) {
			if(e.getCause() instanceof ConstraintViolationException cve) {
				Set<ConstraintViolation<?>> errores = cve.getConstraintViolations();
				throw new LogicaNegocioException(errores, "Los datos no son válidos", e);
			}
			
			throw new LogicaNegocioException("Error de acceso a datos: no se ha podido insertar", e);
		} catch (Exception e) {
			throw new LogicaNegocioException("Error desconocido: no se ha podido insertar", e);
		}
	}

	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = daoUsuario.obtenerPorEmail(email);
		
		if(usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			return usuario;
		} else {
			return Optional.empty();
		}
	}

}
