package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class ChessGameException extends Exception {
	public ChessGameException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ChessGameException(String message) {
		super(message);
	}
}
