package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class InvalidColorException extends Exception {
	public InvalidColorException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidColorException(String message) {
		super(message);
	}
}
