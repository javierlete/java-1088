package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import com.ipartek.formacion.ejemplos.ipartube.pruebas.VideoCrudPruebas;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class Global {
	static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.modelos");

	static {
		VideoCrudPruebas.main(null);
	}
}
