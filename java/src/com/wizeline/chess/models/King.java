package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public class King extends Piece {
	public King(String color, String position) throws InvalidColorException, InvalidPositionException {
		super(color, position);
		this.setCode(color + "K");
		// TODO Auto-generated constructor stub
	}
}
