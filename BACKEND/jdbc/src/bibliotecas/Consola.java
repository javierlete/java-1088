package bibliotecas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	private static final String STRING_FORMATO_FECHA = "dd/MM/yyyy";
	private static final String STRING_FORMATO_HORA = "HH:mm";

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(STRING_FORMATO_FECHA);
	@SuppressWarnings("unused")
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern(STRING_FORMATO_HORA);

	public static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern(STRING_FORMATO_FECHA + " " + STRING_FORMATO_HORA);
	
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
		return leerString(mensaje, OPCIONAL);
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

	public static Integer leerInt(String mensaje) {
		return leerInt(mensaje, OPCIONAL);
	}

	public static Integer leerInt(String mensaje, boolean requerido) {
		do {
			try {
				String texto = leerString(mensaje, requerido);

				if (!requerido && texto == null) {
					return null;
				}

				return Integer.parseInt(texto);
			} catch (NumberFormatException e) {
				pl("No se ha introducido un número");
			}
		} while (true);
	}

	public static LocalDateTime leerLocalDateTime(String mensaje) {
		return leerLocalDateTime(mensaje, OPCIONAL);
	}

	public static LocalDateTime leerLocalDateTime(String mensaje, boolean requerido) {
		do {
			try {
				String texto = leerString(mensaje + " (DD/MM/AAAA HH:MM)", requerido);

				if (!requerido && texto == null) {
					return null;
				}

				return LocalDateTime.parse(texto, FORMATO_FECHA_HORA);
			} catch (DateTimeParseException e) {
				pl("No se ha introducido una fecha con hora en el formato correcto");
			}
		} while (true);
	}
	
	public static LocalDate leerLocalDate(String mensaje) {
		return leerLocalDate(mensaje, OPCIONAL);
	}
	
	public static LocalDate leerLocalDate(String mensaje, boolean requerido) {
		do {
			try {
				String texto = leerString(mensaje + " (DD/MM/AAAA)", requerido);
				
				if (!requerido && texto == null) {
					return null;
				}
				
				return LocalDate.parse(texto, FORMATO_FECHA);
			} catch (DateTimeParseException e) {
				pl("No se ha introducido una fecha en el formato correcto");
			}
		} while (true);
	}
}
