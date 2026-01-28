package com.ipartek.formacion.ejemplos.ipartube.modelos;

public record Usuario(Long id, String imagenUrl, String nombre, String email, String password, Rol rol) {

}
