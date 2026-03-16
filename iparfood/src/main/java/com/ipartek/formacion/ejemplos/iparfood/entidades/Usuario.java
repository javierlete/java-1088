package com.ipartek.formacion.ejemplos.iparfood.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 20)
	private String nombre;
	
	@NotBlank
	@Size(max = 255)
	private String direccion;
	
	@NotBlank
	@Size(max = 255)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 100)
	private String password;

	@NotBlank
	@Size(max = 20)
	@Builder.Default
	private String rol = "USUARIO";
}
