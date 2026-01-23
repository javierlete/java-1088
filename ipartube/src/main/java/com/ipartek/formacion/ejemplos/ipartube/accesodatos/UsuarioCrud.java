package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class UsuarioCrud {
	public static Usuario obtenerPorEmail(String email) {
		if("javier@email.net".equals(email)) {
			return new Usuario(1L, "javier@email.net", "contra");
		}
		
		return null;
	}

}
