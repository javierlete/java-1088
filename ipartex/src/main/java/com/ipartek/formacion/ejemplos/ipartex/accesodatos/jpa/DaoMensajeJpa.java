package com.ipartek.formacion.ejemplos.ipartex.accesodatos.jpa;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

public class DaoMensajeJpa extends DaoJpa<Mensaje> implements DaoMensaje {

	public DaoMensajeJpa() {
		super(Mensaje.class, "com.ipartek.formacion.ejemplos.ipartex.entidades");
	}
	
}
