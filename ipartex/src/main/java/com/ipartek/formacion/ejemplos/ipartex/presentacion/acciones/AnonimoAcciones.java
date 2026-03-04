package com.ipartek.formacion.ejemplos.ipartex.presentacion.acciones;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

public class AnonimoAcciones {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");
	
	@Ruta("/index")
	public static String index(Modelo modelo) {
		modelo.salida().put("mensajes", NEGOCIO.listarMensajes());
		
		return "index";
	}
}
