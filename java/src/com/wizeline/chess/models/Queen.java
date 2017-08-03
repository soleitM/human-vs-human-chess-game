package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class Queen extends Piece {
	public Queen(String color, int row, int col) throws InvalidColorException, InvalidPositionException {
		super(color, row, col);
		this.setCode(color + "Q");
		// TODO Auto-generated constructor stub
	}
}
