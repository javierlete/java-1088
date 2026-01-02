package bibliotecas;

import java.util.Scanner;

public class Consola {
	public static final boolean REQUERIDO = true;
	public static final boolean OPCIONAL = false;

	private static final Scanner SC = new Scanner(System.in);

	public static void pl(String mensaje) {
		System.out.println(mensaje);
	}

	public static void p(String mensaje) {
		System.out.print(mensaje);
	}

	public static void pf(String mensaje, Object... parametros) {
		System.out.printf(mensaje, parametros);
	}

	public static String leerString(String mensaje) {
		return leerString(mensaje, false);
	}

	public static String leerString(String mensaje, boolean requerido) {
		String resultado;

		do {
			p(mensaje + ": ");
			resultado = SC.nextLine();

			if (resultado.isBlank()) {
				if (requerido) {
					pl("Este dato es obligatorio");
				} else {
					resultado = null;
				}
			} else {
				resultado = resultado.trim();
			}
		} while (requerido && resultado.isBlank());

		return resultado;
	}

	public static int leerInt(String mensaje) {
		do {
			try {
				return Integer.parseInt(leerString(mensaje));
			} catch (NumberFormatException e) {
				pl("No se ha introducido un número");
			}
		} while (true);
	}
}
