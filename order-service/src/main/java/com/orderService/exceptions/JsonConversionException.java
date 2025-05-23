package com.orderService.exceptions;

public class JsonConversionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonConversionException(String message) {
		super(message);
	}

}
