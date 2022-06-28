package com.ty.springboothospitalmgmt.exception;

public class NoIdFoundException extends RuntimeException {

	private String message = "Id doesn't exists";

	public NoIdFoundException() {
	}

	public NoIdFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
