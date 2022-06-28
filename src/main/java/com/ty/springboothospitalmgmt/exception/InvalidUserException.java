package com.ty.springboothospitalmgmt.exception;

public class InvalidUserException extends RuntimeException {

	private String message = "Credentials are incorrect";

	public InvalidUserException() {
	}

	public InvalidUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
