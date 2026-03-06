package com.ipartek.formacion.ejemplos.bibliotecas.logicanegocio;

import java.util.Map;
import java.util.Set;

import com.ipartek.formacion.ejemplos.bibliotecas.validaciones.Validacion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class LogicaNegocioException extends RuntimeException {

	private Map<String, String> errores;
	
	public LogicaNegocioException() {
		super();
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public LogicaNegocioException(Set<ConstraintViolation<?>> errores, String message, Throwable cause) {
		super(message, cause);
		this.errores = Validacion.constraintViolationsAErrores(errores);
	}

	public LogicaNegocioException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


	public LogicaNegocioException(String message, Throwable cause) {
		super(message, cause);
		
		if(cause.getCause() instanceof ConstraintViolationException cve) {
			Set<ConstraintViolation<?>> errores = cve.getConstraintViolations();
			this.errores = Validacion.constraintViolationsAErrores(errores);
		}
	}

	public LogicaNegocioException(String message) {
		super(message);
	}

	public LogicaNegocioException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 5676861950458478835L;

}
