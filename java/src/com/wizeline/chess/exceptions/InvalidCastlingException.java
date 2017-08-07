package com.wizeline.chess.exceptions;

@SuppressWarnings("serial")
public class InvalidCastlingException extends Exception {
   public InvalidCastlingException(String message, Throwable throwable) {
	   super(message, throwable);
   }
   public InvalidCastlingException(String message) {
	   super(message);
   }
}
