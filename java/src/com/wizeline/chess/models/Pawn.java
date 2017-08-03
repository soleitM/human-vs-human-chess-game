package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Piece;;

public class Pawn extends Piece {

	public Pawn(String color, int row, int col) throws InvalidColorException, InvalidPositionException {
		super(color, row, col);
		this.setCode(color + "P");
		// TODO Auto-generated constructor stub
	}

}
