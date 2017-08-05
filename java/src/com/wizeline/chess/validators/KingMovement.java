package com.wizeline.chess.validators;

public class KingMovement extends PieceMovement implements MovementValidator {

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		// TODO Auto-generated method stub
		return utility.isKingMovement(originCol, originRow, targetCol, targetRow);
	}

}
