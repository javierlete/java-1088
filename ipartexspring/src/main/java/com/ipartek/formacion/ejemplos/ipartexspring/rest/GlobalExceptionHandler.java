package com.ipartek.formacion.ejemplos.ipartexspring.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errores = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errores);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleValidation(ConstraintViolationException ex) {

		Map<String, String> errores = new HashMap<>();

		ex.getConstraintViolations()
				.forEach(error -> errores.put(error.getPropertyPath().toString(), error.getMessage()));

		return ResponseEntity.badRequest().body(errores);
	}

}
