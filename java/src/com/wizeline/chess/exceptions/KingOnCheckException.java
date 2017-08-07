package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class KingOnCheckException extends Exception{

	public KingOnCheckException(String message, Throwable throwable) {
		super(message, throwable);
		// TODO Auto-generated constructor stub
	}

	public KingOnCheckException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
