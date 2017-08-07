package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends GameControllerException {
	public InvalidPositionException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidPositionException(String message) {
		super(message);
	}
}
