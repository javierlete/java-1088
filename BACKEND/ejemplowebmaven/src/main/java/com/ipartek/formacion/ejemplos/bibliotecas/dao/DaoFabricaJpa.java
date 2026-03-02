package com.ipartek.formacion.ejemplos.bibliotecas.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFabricaJpa {
	private static final Properties p = new Properties();
	private static final Map<String, DaoJpa> daos = new HashMap<>();
	
	static {
		try {
			p.load(DaoFabricaJpa.class.getResourceAsStream("/configuracion.properties"));
		} catch (IOException e) {
			throw new DaoException("No se ha podido leer el fichero de configuración", e);
		}
	}
	
	public static DaoJpa getDaoJpa(String clave) {
		if(daos.containsKey(clave)) {
			return daos.get(clave);
		}
		
		DaoJpa daoJpa = new DaoJpa(p.getProperty(clave));
		
		daos.put(clave, daoJpa);
		
		return daoJpa;
	}
}
