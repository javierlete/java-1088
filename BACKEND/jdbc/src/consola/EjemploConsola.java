package consola;

import java.util.Scanner;

public class EjemploConsola {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime tu nombre");
		
		String nombre = sc.nextLine();
		
//		System.out.println("Hola " + nombre);
		System.out.printf("Hola %s", nombre);
		
		sc.close();
	}
}
