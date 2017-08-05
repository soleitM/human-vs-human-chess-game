package com.wizeline.chess.models;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.validators.PawnMovement;;

public class Pawn extends Piece {

	public Pawn(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		setMovementValidator(new PawnMovement());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.PAWN;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		int direction = (this.getColor() == Board.WHITE_PIECE) ? 1 : -1;
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, direction);
	}

	

}
