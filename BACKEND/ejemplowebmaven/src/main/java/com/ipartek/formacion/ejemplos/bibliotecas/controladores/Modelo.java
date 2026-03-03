package com.ipartek.formacion.ejemplos.bibliotecas.controladores;

import java.util.Map;

public record Modelo(Map<String, String[]> entrada, Map<String, Object> salida,
		Map<String, Object> sesionEntrada, Map<String, Object> sesionSalida) {

}
