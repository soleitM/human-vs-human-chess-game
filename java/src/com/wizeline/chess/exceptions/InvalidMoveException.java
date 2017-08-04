package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public InvalidMoveException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidMoveException(String message) {
		super(message);
	}
}
