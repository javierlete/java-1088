package com.ipartek.formacion.ejemplos.bibliotecas.dao;

import java.io.IOException;
import java.util.Properties;

public class DaoFabricaJpa {
	private static final Properties p = new Properties();
	
	static {
		try {
			p.load(DaoFabricaJpa.class.getResourceAsStream("/configuracion.properties"));
		} catch (IOException e) {
			throw new DaoException("No se ha podido leer el fichero de configuración", e);
		}
	}
	
	public static DaoJpa getDaoJpa(String clave) {
		return new DaoJpa(p.getProperty(clave));
	}
}
