package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.RookMovement;

public class Rook extends Piece {

	private boolean moved;

	public Rook(String color) throws InvalidColorException, InvalidPositionException {
		super(color);
		setMovementValidator(new RookMovement());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getColor() + Piece.ROOK;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, boolean specialMove) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
	}

	public boolean canCastle() {
		return !this.moved;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		this.moved = true;
		
	}
}
