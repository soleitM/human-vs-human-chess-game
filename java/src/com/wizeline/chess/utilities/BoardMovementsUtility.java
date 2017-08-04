package com.wizeline.chess.utilities;

public class BoardMovementsUtility {
	
private int horizontalDistance(char originCol, char targetCol) {
	return Math.abs(targetCol - originCol); 
}
private int verticalDistance(char originRow, char targetRow) {
	return Math.abs(targetRow - originRow); 
}
   private boolean isSingleMoveForward(char originCol, char originRow, char targetCol, char targetRow, int direction) {
	   /*char originCol = origin.charAt(0);
	   char originRow = origin.charAt(1);
	   char targetCol = target.charAt(0);
	   char targetRow = target.charAt(1);*/
	   return (targetCol == originCol) && (targetRow - originRow == direction);
   }
  
   private boolean isSingleMoveHorizontal(char originCol, char originRow, char targetCol, char targetRow) {
	   return (targetRow == originRow) && (horizontalDistance(targetCol,originCol) == 1);
   }
   private boolean isSingleMoveVertical(char originCol, char originRow, char targetCol, char targetRow) {
	   return isSingleMoveForward(originCol,originRow,targetCol,targetRow,1) ||
			   isSingleMoveForward(originCol,originRow,targetCol,targetRow,-1);
   }
   private boolean isSingleMoveDiagonal(char originCol, char originRow, char targetCol, char targetRow) {
	   return (horizontalDistance(targetCol , originCol) == 1) && (verticalDistance(targetRow, originRow) == 1);
   }
   private boolean isHorizontalMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return (targetRow == originRow) && (targetCol != originCol);
   }
   private boolean isVerticalMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return (targetRow != originRow) && (targetCol == originCol);
   }
   private boolean isDiagonalMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return (horizontalDistance(targetCol, originCol)) == (verticalDistance(targetRow, originRow) );
   }
   private boolean isLShapedMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return ((horizontalDistance(originCol, targetCol) == 1) && (verticalDistance(originRow, targetRow) == 2)) || 
			   ((horizontalDistance(originCol, targetCol) == 2) && (verticalDistance(originRow, targetRow) == 1));
   }
   
   public boolean isKingMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return isSingleMoveVertical(originCol, originRow, targetCol, targetRow) || 
			   isSingleMoveHorizontal(originCol, originRow, targetCol, targetRow) ||
			   isSingleMoveDiagonal(originCol, originRow, targetCol, targetRow) ;
			   
   }
   public boolean isRookMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return isHorizontalMovement(originCol, originRow, targetCol, targetRow);
   }
   public boolean isBishopMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return isDiagonalMovement(originCol, originRow, targetCol, targetRow);
   }
   public boolean isQueenMovement(char originCol, char originRow, char targetCol, char targetRow) {
	   return false;
   }
       
}
