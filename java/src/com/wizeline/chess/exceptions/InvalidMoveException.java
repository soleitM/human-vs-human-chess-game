package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	private static final String DEFAULT_MESSAGE = "Invalid Move. ";
	public InvalidMoveException(String message, Throwable throwable) {
		super(DEFAULT_MESSAGE + message, throwable);
	}

	public InvalidMoveException(String message) {
		super(DEFAULT_MESSAGE + message);
	}
}
