package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class King extends Piece {
	public King(String color, int row, int col) throws InvalidColorException, InvalidPositionException {
		super(color, row, col);
		this.setCode(color + "K");
		// TODO Auto-generated constructor stub
	}
}
