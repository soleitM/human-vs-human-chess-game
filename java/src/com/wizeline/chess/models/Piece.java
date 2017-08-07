package com.wizeline.chess.models;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.validators.MovementValidator;

public abstract class Piece{

	protected static final String KING = "K";
	protected static final String QUEEN = "Q";
	protected static final String BISHOP = "B";
	protected static final String ROOK = "R";
	protected static final String KNIGHT = "N";
	protected static final String PAWN = "P";

	private String color;
	protected MovementValidator movementValidator;

	public Piece() throws UnsupportedOperationException {

	}

	public Piece(String color) throws InvalidColorException, InvalidPositionException {
		if ((!color.equals(Board.WHITE_PIECE)) && (!color.equals(Board.BLACK_PIECE))) {
			throw new InvalidColorException("Invalid input. Color must be White 'w' or Black 'b'");
		}

		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public abstract boolean canMove(char originCol, char originRow, char targetCol, char targetRow, boolean specialMove);

	public abstract String toString();

	public abstract void move();

	public MovementValidator getMovementValidator() {
		return movementValidator;
	}

	public void setMovementValidator(MovementValidator movementValidator) {
		this.movementValidator = movementValidator;
	}

	public boolean canBeBlocked() {
		return !((this instanceof Knight) || (this instanceof King) || (this instanceof Pawn));
	}

}
