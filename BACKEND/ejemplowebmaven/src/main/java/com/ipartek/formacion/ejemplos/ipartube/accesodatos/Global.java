package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class Global {
	static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.modelos");
}
