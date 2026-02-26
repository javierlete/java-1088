package com.ipartek.formacion.oop.pojos;

import static java.lang.Math.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Punto implements Iterable<Integer> {
	private double distancia;
	private double angulo;

	public Punto(int x, int y) {
		setX(x);
		setY(y);
	}

	public Punto() {
		this(0, 0);
	}

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
		return String.format("Punto [x=%s, y=%s, distancia=%s, angulo=%s]", getX(), getY(), distancia, angulo);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int contador = 0;

			@Override
			public boolean hasNext() {
				return contador < 2;
			}

			@Override
			public Integer next() {
				return switch (contador++) {
				case 0 -> getX();
				case 1 -> getY();
				default -> throw new NoSuchElementException();
				};
			}
		};
	}
}
