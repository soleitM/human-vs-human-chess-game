package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public abstract class Piece {
	public static final String WHITE_PIECE = "w";
	public static final String BLACK_PIECE = "b";
	public static final int ROWS = 8;
	public static final int COLS = 8;

	private String color;
	private String position;
	private String code;

	public Piece() throws UnsupportedOperationException {

	}

	public Piece(String color, String position) throws InvalidColorException, InvalidPositionException {
		if ((!color.equals(WHITE_PIECE)) && (!color.equals(BLACK_PIECE))) {
			throw new InvalidColorException("Invalid input. Color must be White 'w' or Black 'b'");
		}

		this.color = color;
		setPosition(position);
	}

	public String getColor() {
		return color;
	}

	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = this.code == null ? code : this.code;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position){
		this.position = position;
	}
}
