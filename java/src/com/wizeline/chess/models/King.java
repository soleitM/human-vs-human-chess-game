package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.KingMovement;

public class King extends Piece {
	public King(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		this.setMovementValidator(new KingMovement());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.KING;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
	}

	

}
