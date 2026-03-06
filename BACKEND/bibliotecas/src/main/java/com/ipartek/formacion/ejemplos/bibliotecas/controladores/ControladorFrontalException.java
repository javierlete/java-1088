package com.ipartek.formacion.ejemplos.bibliotecas.controladores;

public class ControladorFrontalException extends RuntimeException {

	private static final long serialVersionUID = 152481294395165477L;

	public ControladorFrontalException() {
		super();
	}

	public ControladorFrontalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ControladorFrontalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControladorFrontalException(String message) {
		super(message);
	}

	public ControladorFrontalException(Throwable cause) {
		super(cause);
	}

	
}
