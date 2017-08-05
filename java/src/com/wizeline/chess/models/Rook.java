package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.RookMovement;

public class Rook extends Piece {

	public Rook(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		setMovementValidator(new RookMovement());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.ROOK;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
	}

}
