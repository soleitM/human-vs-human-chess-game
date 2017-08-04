package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class Bishop extends Piece {
	public Bishop(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color,position);
		this.setCode(color + "B");
	}

	@Override
	protected boolean canMoveTo(String newPosition) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}
}
