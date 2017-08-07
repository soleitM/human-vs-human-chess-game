package com.wizeline.chess.validators;

public class KnightMovement extends PieceMovement implements MovementValidator {

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		// TODO Auto-generated method stub
		return utility.isKnightMovement(originCol, originRow, targetCol, targetRow);
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction,
			boolean specialMove) {
		return canMove(originCol, originRow, targetCol, targetRow, direction);
	}

	

}
