package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.KingMovement;

public class King extends Piece {

	private boolean moved;

	public King(String color) throws InvalidColorException, InvalidPositionException {
		super(color);
		this.moved = false;
		this.setMovementValidator(new KingMovement());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + Piece.KING;
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, boolean specialMove) {
		// TODO Auto-generated method stub
		return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0, specialMove && !this.moved);
	}

//	public boolean canCastle() {
//		return (!this.moved) && (!this.onCheck);
//	}


//	public void onCheck() {
//		this.onCheck = true;
//	}
//
//	public void unCheck() {
//		this.onCheck = false;
//	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		this.moved = true;
	}
}
