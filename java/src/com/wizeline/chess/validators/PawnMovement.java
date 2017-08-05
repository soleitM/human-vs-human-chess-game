package com.wizeline.chess.validators;

public class PawnMovement extends PieceMovement implements MovementValidator {

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		return utility.isPawnMovement(originCol, originRow, targetCol, targetRow, direction);
	}

	

}
