package com.ipartek.formacion.ejemplos.bibliotecas.fabrica;

public class FabricaException extends RuntimeException {

	public FabricaException() {
		super();
	}

	public FabricaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FabricaException(String message, Throwable cause) {
		super(message, cause);
	}

	public FabricaException(String message) {
		super(message);
	}

	public FabricaException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -3032769917042361415L;

}
