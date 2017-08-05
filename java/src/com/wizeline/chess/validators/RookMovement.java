package com.wizeline.chess.validators;

public class RookMovement extends PieceMovement implements MovementValidator {

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		// TODO Auto-generated method stub
		return utility.isRookMovement(originCol, originRow, targetCol, targetRow);
	}

	

}
