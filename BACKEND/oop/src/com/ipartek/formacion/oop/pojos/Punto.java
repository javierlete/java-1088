package com.ipartek.formacion.oop.pojos;

import static java.lang.Math.*;

public class Punto {
	private double distancia;
	private double angulo;

	public int getX() {
		return (int) round(distancia * cos(toRadians(angulo)));
	}

	public void setX(int x) {
		int y = getY();

		distancia = sqrt(pow(x, 2) + pow(y, 2));
		angulo = toDegrees(atan2(y, x));
	}

	public int getY() {
		return (int) round(distancia * sin(toRadians(angulo)));
	}

	public void setY(int y) {
		int x = getX();

		distancia = sqrt(pow(x, 2) + pow(y, 2));
		angulo = toDegrees(atan2(y, x));
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	@Override
	public String toString() {
		return "Punto [distancia=" + distancia + ", angulo=" + angulo + "]";
	}
}
