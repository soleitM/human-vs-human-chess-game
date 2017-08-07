package com.wizeline.chess.models;

import java.util.HashSet;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.BishopMovement;

public class Bishop extends Piece {
	public Bishop(String color) throws InvalidColorException, InvalidPositionException {
		super(color);
		setMovementValidator(new BishopMovement());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.BISHOP;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, boolean specialMove) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
