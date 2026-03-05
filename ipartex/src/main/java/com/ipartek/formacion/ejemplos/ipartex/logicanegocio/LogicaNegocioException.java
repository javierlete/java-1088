package com.ipartek.formacion.ejemplos.ipartex.logicanegocio;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class LogicaNegocioException extends RuntimeException {

	private Set<ConstraintViolation<?>> errores;
	
	public LogicaNegocioException() {
		super();
	}

	public Set<ConstraintViolation<?>> getErrores() {
		return errores;
	}

	public LogicaNegocioException(Set<ConstraintViolation<?>> errores, String message, Throwable cause) {
		super(message, cause);
		this.errores = errores;
	}

	public LogicaNegocioException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


	public LogicaNegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicaNegocioException(String message) {
		super(message);
	}

	public LogicaNegocioException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 5676861950458478835L;

}
