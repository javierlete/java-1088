package com.ipartek.formacion.ejemplos.bibliotecas.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.ScanResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
		String ruta = request.getPathInfo();
		HttpSession session = request.getSession();

		Map<String, String[]> entrada = request.getParameterMap();

		Map<String, Object> sesionEntrada = new HashMap<>();

		Enumeration<String> variablesDeSesion = session.getAttributeNames();

		while (variablesDeSesion.hasMoreElements()) {
			String variableDeSesion = variablesDeSesion.nextElement();
			sesionEntrada.put(variableDeSesion, session.getAttribute(variableDeSesion));
		}

		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio
		Map<String, Object> salida = new HashMap<>();
		Map<String, Object> sesionSalida = new HashMap<>();

		String rutaDestino = ejecutarAccion(ruta, entrada, salida, sesionEntrada, sesionSalida);

		if (rutaDestino == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"No se ha podido ejecutar la ruta o no existe: " + ruta);
			return;
		}

		// Empaquetar modelo para la siguiente vista
		request.setAttribute("ahora", LocalDateTime.now());

		salida.forEach((clave, valor) -> request.setAttribute(clave, valor));
		sesionSalida.forEach((clave, valor) -> session.setAttribute(clave, valor));

		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/" + rutaDestino + ".jsp").forward(request, response);

	}

	private String ejecutarAccion(String ruta, Map<String, String[]> entrada, Map<String, Object> salida,
			Map<String, Object> sesionEntrada, Map<String, Object> sesionSalida) {
		try (ScanResult scan = new ClassGraph().enableAllInfo()
				.acceptPackages("com.ipartek.formacion.ejemplos.ipartube.acciones").scan()) {

			// Buscamos las clases que contienen métodos con @Ruta
			for (ClassInfo ci : scan.getClassesWithMethodAnnotation(Ruta.class)) {

				// Sacamos la información de cada método de esas clases
				for (MethodInfo mi : ci.getMethodInfo()) {
				
					// Si ese método tiene @Ruta y la @Ruta(value) es igual que la ruta recibida...
					if (mi.hasAnnotation(Ruta.class)
							&& ruta.equals(mi.getAnnotationInfo(Ruta.class).getParameterValues().getValue("value"))) {
					
						// ... obtenemos el método de Reflection
						Method m = mi.loadClassAndGetMethod();
						
						// ... y lo invocamos
						return (String) m.invoke(null, new Modelo(entrada, salida, sesionEntrada, sesionSalida));
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
