package com.tshirtmart.trilo.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationExceptionHandler extends Exception {

	private static final long serialVersionUID = 1L;

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public static Map<String, String> invalidExceptionHandler(MethodArgumentNotValidException exception) {

		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {

			errors.put(error.getField(), error.getDefaultMessage());

		});

		return errors;

	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Map<String,String> DuplicateEmailException(DataIntegrityViolationException ex){
		
		Map<String, String> errors = new HashMap<>();
	
		errors.put("Duplicate Entry","Email is Already Registered!");

		return errors;
		
		
	}
}
