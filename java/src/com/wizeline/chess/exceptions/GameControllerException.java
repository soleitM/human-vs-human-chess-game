package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class GameControllerException extends ChessGameException {
	public GameControllerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public GameControllerException(String message) {
		super(message);
	}
}
