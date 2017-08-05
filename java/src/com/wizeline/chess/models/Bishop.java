package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.BishopMovement;

public class Bishop extends Piece {
	public Bishop(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		setMovementValidator(new BishopMovement());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.BISHOP;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
	}
}
