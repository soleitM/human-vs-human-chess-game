package com.wizeline.chess.models;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.PawnMovement;;

public class Pawn extends Piece {

	private boolean moved;
	private boolean enPassant;
	private boolean promoted;

	public Pawn(String color) throws InvalidColorException, InvalidPositionException {
		super(color);
		setMovementValidator(new PawnMovement());
		moved = false;
		promoted = false;
		enPassant = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getColor() + ((promoted) ? Piece.QUEEN : Piece.PAWN);
	}

	@Override
	public boolean canMove(char originCol, char originRow, char targetCol, char targetRow, boolean specialMove) {
		// TODO Auto-generated method stub
		if(this.isPromoted()) return movementValidator.canMove(originCol, originRow, targetCol, targetRow, 0);
		int singleMoveDirection = (this.getColor() == Board.WHITE_PIECE) ? 1 : -1;
		int doubleMoveDirection = (this.getColor() == Board.WHITE_PIECE) ? 2 : -2;
		if(specialMove) return movementValidator.canMove(originCol, originRow, targetCol, targetRow, singleMoveDirection,true);
		if (movementValidator.canMove(originCol, originRow, targetCol, targetRow, singleMoveDirection)) {
			return true;
		} else if (!moved
				&& (movementValidator.canMove(originCol, originRow, targetCol, targetRow, doubleMoveDirection))) {
			//this.markEnPassant();
			return true;
		} else
			return false;
	}

	public void markEnPassant() {
		// TODO Auto-generated method stub
		this.enPassant = true;

	}

	public boolean isEnPassant() {
		return this.enPassant;
	}

	public void unMarkEnPassant() {
		this.enPassant = false;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		moved = true;
	}
    
	public void makePromoted() {
		this.promoted = true;
	}
	public boolean isPromoted() {
		return promoted;
	}
}
