package com.ipartek.formacion.oop.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Poligono {
	private String nombre;
	private ArrayList<Punto> puntos = new ArrayList<>();

	public Poligono(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Punto> getPuntos() {
		return puntos;
	}

	// MÉTODOS DE INSTANCIA
	public void puntoNuevo(Punto punto) {
		if (punto != null) {
			puntos.add(punto);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, puntos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poligono other = (Poligono) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(puntos, other.puntos);
	}

	@Override
	public String toString() {
		return "Poligono [nombre=" + nombre + ", puntos=" + puntos + "]";
	}

}
