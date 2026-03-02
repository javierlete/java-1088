package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoFabricaJpa;
import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.pruebas.VideoDaoPruebas;

class Configuracion {
	static final DaoJpa DAO = DaoFabricaJpa.getDaoJpa("daojpa.unidadpersistencia");
	
	static {
		VideoDaoPruebas.main(null);
	}
}
