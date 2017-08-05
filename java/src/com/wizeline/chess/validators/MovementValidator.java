package com.wizeline.chess.validators;

public interface MovementValidator {
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction);
}
