package com.ipartek.formacion.ejemplos.bibliotecas.fabrica;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoException;
import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoFabricaJpa;

public class Fabrica {
	private static final Properties p = new Properties();
	private static final Map<String, Object> objetos = new HashMap<>();

	static {
		try {
			p.load(DaoFabricaJpa.class.getResourceAsStream("/configuracion.properties"));
		} catch (IOException e) {
			throw new DaoException("No se ha podido leer el fichero de configuración", e);
		}
	}

	public static Object getObjeto(String clave) {
		String nombreClase = null;

		try {
			nombreClase = p.getProperty(clave); // "com.ipartek.formacion.ejemplos.ipartube.daos.DaoRolJpa"
			
			if(objetos.containsKey(nombreClase)) {
				return objetos.get(nombreClase);
			}

			Class<?> clase = Class.forName(nombreClase); // com.ipartek.formacion.ejemplos.ipartube.daos.DaoRolJpa
			Constructor<?> constructor = clase.getConstructor(); // com.ipartek.formacion.ejemplos.ipartube.daos.DaoRolJpa()
			Object objeto = constructor.newInstance(); // new com.ipartek.formacion.ejemplos.ipartube.daos.DaoRolJpa()

			objetos.put(nombreClase, objeto);
			
			return objeto;
		} catch (ClassNotFoundException e) {
			throw new FabricaException("No se ha encontrado la clase " + nombreClase, e);
		} catch (NoSuchMethodException e) {
			throw new FabricaException("No se ha encontrado el constructor vacío de la clase " + nombreClase, e);
		} catch (SecurityException e) {
			throw new FabricaException("No se ha podido acceder al constructor vacío de la clase " + nombreClase, e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new FabricaException("No se ha podido invocar el constructor vació de la clase " + nombreClase, e);
		}
	}
}
