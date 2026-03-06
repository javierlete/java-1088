package com.ipartek.formacion.ejemplos.bibliotecas.validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class Validacion {
	public static Map<String, String> constraintViolationsAErrores(Set<ConstraintViolation<?>> constraintViolations) {
		Map<String, String> errores = new HashMap<>();

		constraintViolations.forEach(cv -> {
			String campo = cv.getPropertyPath().toString();
			String mensaje = cv.getMessage();
			
			if (errores.containsKey(campo)) {
				errores.put(campo, errores.get(campo) + ", " + mensaje);
			} else {
				errores.put(campo, mensaje);
			}
		});

		return errores;
	}
}
