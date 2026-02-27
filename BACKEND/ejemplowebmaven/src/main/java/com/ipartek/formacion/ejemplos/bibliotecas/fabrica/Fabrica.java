package com.ipartek.formacion.ejemplos.bibliotecas.fabrica;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoException;
import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoFabricaJpa;

public class Fabrica {
	private static final Properties p = new Properties();

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
			nombreClase = p.getProperty(clave);

			Class<?> clase = Class.forName(nombreClase);
			Constructor<?> constructor = clase.getConstructor();
			Object objeto = constructor.newInstance();

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
