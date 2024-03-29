package com.wizeline.chess.utilities;

public class BoardMovementsUtility {
    
	
	private int horizontalDistance(char originCol, char targetCol) {
		//System.out.println(Math.abs(targetCol - originCol));
		return Math.abs(targetCol - originCol);
	}

	private int verticalDistance(char originRow, char targetRow) {
		//System.out.println(Math.abs(targetRow - originRow));
		return Math.abs(targetRow - originRow);
	}
	
	public int getDirection(char initial, char end) {
		if(end == initial) return 0;
		return (end > initial ) ? 1 : -1;
	}
	public boolean adjacent(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		return (horizontalDistance(targetCol, originCol) == 1) && (verticalDistance(targetRow, originRow) == 0);
	}
	public boolean isDoubleMoveForward(String origin, String target) {
		return verticalDistance(origin.charAt(1), target.charAt(1)) == 2;
	}

	private boolean isSingleMoveForward(char originCol, char originRow, char targetCol, char targetRow, int direction) {

		return horizontalDistance(targetCol,originCol) == 0 && (targetRow - originRow == direction);
	}

	private boolean isSingleMoveHorizontal(char originCol, char originRow, char targetCol, char targetRow) {
		return verticalDistance(originRow, targetRow) == 0 && (horizontalDistance(targetCol, originCol) == 1);
	}

	private boolean isSingleMoveVertical(char originCol, char originRow, char targetCol, char targetRow) {
		return isSingleMoveForward(originCol, originRow, targetCol, targetRow, 1)
				|| isSingleMoveForward(originCol, originRow, targetCol, targetRow, -1);
	}

	public boolean isSingleMoveDiagonal(char originCol, char originRow, char targetCol, char targetRow) {
		return (horizontalDistance(targetCol, originCol) == 1) && (verticalDistance(targetRow, originRow) == 1);
	}

	public boolean isHorizontalMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return verticalDistance(originRow, targetRow) == 0 && horizontalDistance(originCol, targetCol) != 0;
	}

	public boolean isVerticalMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return verticalDistance(originRow, targetRow) != 0 && horizontalDistance(originCol, targetCol) == 0;
	}

	public boolean isDiagonalMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return (horizontalDistance(targetCol, originCol)) == (verticalDistance(targetRow, originRow));
	}
    public boolean isCastlingLikeMovement(char originCol, char originRow, char targetCol, char targetRow) {
    	return verticalDistance(originRow, targetRow) == 0 && horizontalDistance(originCol, targetCol) == 2;
    }
	private boolean isLShapedMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return ((horizontalDistance(originCol, targetCol) == 1) && (verticalDistance(originRow, targetRow) == 2))
				|| ((horizontalDistance(originCol, targetCol) == 2) && (verticalDistance(originRow, targetRow) == 1));
	}

	public boolean isKingMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return isSingleMoveVertical(originCol, originRow, targetCol, targetRow)
				|| isSingleMoveHorizontal(originCol, originRow, targetCol, targetRow)
				|| isSingleMoveDiagonal(originCol, originRow, targetCol, targetRow);

	}

	public boolean isRookMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return isHorizontalMovement(originCol, originRow, targetCol, targetRow)
				|| isVerticalMovement(originCol, originRow, targetCol, targetRow);
	}

	public boolean isBishopMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return isDiagonalMovement(originCol, originRow, targetCol, targetRow);
	}

	public boolean isQueenMovement(char originCol, char originRow, char targetCol, char targetRow) {
		return isRookMovement(originCol, originRow, targetCol, targetRow)
				|| isBishopMovement(originCol, originRow, targetCol, targetRow);
	}

	public boolean isKnightMovement(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		return isLShapedMovement(originCol, originRow, targetCol, targetRow);
	}

	public boolean isPawnMovement(char originCol, char originRow, char targetCol, char targetRow, int direction) {
		return isSingleMoveForward(originCol, originRow, targetCol, targetRow, direction);
	}
    public boolean isPawnCaptureMovement(char originCol, char originRow, char targetCol, char targetRow, int direction) {
    	return horizontalDistance(originCol, targetCol) == 1 && (targetRow - originRow == direction);
    }
}
