package com.wizeline.chess.validators;

public class KingMovement extends PieceMovement implements MovementValidator {

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		// TODO Auto-generated method stub
		return utility.isKingMovement(originCol, originRow, targetCol, targetRow);
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, int direction,
			boolean specialMove) {
		// TODO Auto-generated method stub
		return(specialMove)? utility.isCastlingLikeMovement(originCol, originRow, targetCol, targetRow) : canMove(originCol, originRow, targetCol, targetRow, direction);
	}

}
