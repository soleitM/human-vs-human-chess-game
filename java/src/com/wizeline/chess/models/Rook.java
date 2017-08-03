package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class Rook extends Piece {

	public Rook(String color, int row, int col) throws InvalidColorException, InvalidPositionException {
		super(color, row, col);
		this.setCode(color + "R");
		// TODO Auto-generated constructor stub
	}

}
