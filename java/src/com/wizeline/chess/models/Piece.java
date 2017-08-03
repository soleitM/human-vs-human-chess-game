package com.wizeline.chess.models;

import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;

public abstract class Piece {
	public static final String WHITE_PIECE = "w";
	public static final String BLACK_PIECE = "b";
	public static final int ROWS = 8;
	public static final int COLS = 8;

	private String color;
	private int row;
	private int col;
	private String code;

	public Piece() throws UnsupportedOperationException {

	}

	public Piece(String color, int row, int col) throws InvalidColorException, InvalidPositionException {
		if ((!color.equals(WHITE_PIECE)) && (!color.equals(BLACK_PIECE))) {
			throw new InvalidColorException("Invalid input. Color must be White 'w' or Black 'b'");
		}

		this.color = color;
		setRow(row);
		setCol(col);
	}

	public String getColor() {
		return color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) throws InvalidPositionException {
		if (row < 1 || row > ROWS) {
			throw new InvalidPositionException("Invalid input. Row must be a value between 1 and 8");
		}
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) throws InvalidPositionException {
		if (col < 1 || col > COLS) {
			throw new InvalidPositionException("Invalid input. Column must be a value between 1 and 8");
		}
		this.col = col;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = this.code == null ? code : this.code;
	}
}
