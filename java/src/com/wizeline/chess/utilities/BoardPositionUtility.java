package com.wizeline.chess.utilities;

import com.wizeline.chess.Board;
import com.wizeline.chess.models.Piece;

public class BoardPositionUtility {
	
	private static final int LEFT = -1;
	private static final int RIGHT = 1;
	private static final int UP = 1;
	private static final int DOWN = -1;
	
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
	
//	public void movePiece(Board board,String origin,String target) {
//		Piece movingPiece = board.pieces.get(origin);
//		//changePosition(movingPiece,target);
//        board.pieces.put(target, movingPiece);
//        board.pieces.remove(origin);
//	}
    private String getPositionNSquaresHorizontallyApart(String position, int n, int direction) {
    	char originCol = position.charAt(0);
    	char originRow = position.charAt(1);
    	char targetCol = (char)(originCol + direction);
    	return String.format("%c%c", targetCol,originRow);
    }
	
    public boolean toTheRight(char targetCol, char originCol) {
    	return targetCol > originCol;
    }
    public String getCastlingRookPosition(String kingPosition, boolean rightOrLeft) {
    	int direction = (rightOrLeft) ? LEFT : RIGHT;
    	return getPositionNSquaresHorizontallyApart(kingPosition, 1, direction);
    }
    public String getCornerSquare (String origin, String target) {
    	return String.format("%c%c", target.charAt(0),origin.charAt(1));
    }
}
