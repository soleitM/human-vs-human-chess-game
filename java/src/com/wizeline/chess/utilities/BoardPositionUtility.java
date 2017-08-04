package com.wizeline.chess.utilities;

import com.wizeline.chess.Board;

public class BoardPositionUtility {
	public boolean isOnBoard(String position) {
		char col = position.charAt(0);
		int row = position.charAt(1) - '0';
		return (withinVerticalBorders(row) && withinHorizontalBorders(col));
	}

	private boolean withinHorizontalBorders(char col) {

		return (col >= Board.COLUMN_NAMES[0].charAt(0) && col <= Board.COLUMN_NAMES[Board.COLS - 1].charAt(0));
	}

	private boolean withinVerticalBorders(int row) {
		return (row >= 0 && row <= Board.ROWS);
	}
	
}
