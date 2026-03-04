package com.ipartek.formacion.ejemplos.ipartex.logicanegocio.impl;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	private final DaoMensaje daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje"); 
	
	@Override
	public Mensaje enviarMensaje(Mensaje mensaje) {
		return daoMensaje.insertar(mensaje); 
	}
	
}
