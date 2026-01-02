package bibliotecas;

import java.util.Scanner;

public class Consola {
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
		p(mensaje + ": ");
		return SC.nextLine(); 
	}
	
	public static int leerInt(String mensaje) {
		return Integer.parseInt(leerString(mensaje));
	}
}
