package com.ipartek.formacion.oop.pruebas;

import java.time.LocalDate;

import com.ipartek.formacion.oop.pojos.Empleado;
import com.ipartek.formacion.oop.pojos.Local;
import com.ipartek.formacion.oop.pojos.Persona;

public class EmpleadoPruebas {
	public static void main(String[] args) {
		Empleado empleado = new Empleado("Javier", LocalDate.of(2000, 1, 2), "12345678A");

		System.out.println(empleado);

		Persona persona = empleado; // Generalización implícita

		// System.out.println(persona.getDni()); // No se puede porque Persona no lo
		// tiene definido

		System.out.println(persona.getNombre());

		Empleado empleado2 = (Empleado) persona; // Particularización explícita (casting)

		System.out.println(empleado2.getDni());

		Local local = new Local();

		Persona pepe = new Persona("Pepe");

		local.entrar(pepe);
		local.entrar(empleado);

		for (Persona p : local.getPersonas()) {
			System.out.println(p);
		}

		if (pepe instanceof Empleado) {
			Empleado empleado3 = (Empleado) pepe;

			System.out.println(empleado3.getDni());
		} else {
			System.out.println("Pepe no es un empleado");
		}

		for (Persona p : local.getPersonas()) {
			if (p instanceof Empleado e) {
				// Empleado e = (Empleado) p;
				System.out.println(e.getDni());
			} else {
				System.out.println("No es empleado");
			}
		}
	}
}
