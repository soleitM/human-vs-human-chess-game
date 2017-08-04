package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class Knight extends Piece {
	public Knight(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		this.setCode(color + "N");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean canMoveTo(String newPosition) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}
}
