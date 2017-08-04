package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Piece;;

public class Pawn extends Piece {

	public Pawn(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		this.setCode(color + "P");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean canMoveTo(String newPosition) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}

}
