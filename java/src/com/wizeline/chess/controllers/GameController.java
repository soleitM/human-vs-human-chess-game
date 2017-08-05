package com.wizeline.chess.controllers;

import javax.swing.JLabel;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Bishop;
import com.wizeline.chess.models.Game;
import com.wizeline.chess.models.King;
import com.wizeline.chess.models.Knight;
import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.models.Queen;
import com.wizeline.chess.models.Rook;
import com.wizeline.chess.utilities.BoardMovementsUtility;
import com.wizeline.chess.utilities.BoardPiecesUtility;
import com.wizeline.chess.utilities.BoardPositionUtility;

public class GameController {

	private Board board;
	private PieceFactory pieceFactory;
	private Game game;
	private BoardPositionUtility boardPositionUtility;
	private BoardPiecesUtility boardPiecesUtility;
	private BoardMovementsUtility boardMovementsUtility;

	public GameController() {
		boardPositionUtility = new BoardPositionUtility();
		boardPiecesUtility = new BoardPiecesUtility();
		boardMovementsUtility = new BoardMovementsUtility();
		game = new Game();
	}

	public void initializeBoard() throws InvalidColorException, InvalidPositionException {
		board = new Board();
		pieceFactory = new PieceFactory(board);
		pieceFactory.initializePieces();

	}

	public Board getBoard() {
		return board;
	}

	

	public void makeMove(String origin, String target, JLabel outputLabel){
		try {
			validateOriginPosition(origin);
			Piece movingPiece = board.pieces.get(origin);
			validateMovingPiece(movingPiece);
			validateTargetPosition(movingPiece, target);
			char originCol = origin.charAt(0);
			char originRow = origin.charAt(1);
			char targetCol = target.charAt(0);
			char targetRow = target.charAt(1);
			validatePath(movingPiece, originCol, originRow, targetCol, targetRow);
	        if(movingPiece.canMove(originCol, originRow, targetCol, targetRow)) {
	        	movePiece(origin, target);	
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void validatePath(Piece movingPiece, char originCol, char originRow, char targetCol, char targetRow) throws InvalidMoveException {
		// TODO Auto-generated method stub
		if(movingPiece.canBeBlocked()) {
			checkForAnyPieceAlongThePath(originCol, originRow, targetCol, targetRow);
		}
		
	}

	private void checkForAnyPieceAlongThePath(char originCol, char originRow, char targetCol, char targetRow) throws InvalidMoveException {
		// TODO Auto-generated method stub
//		if(boardMovementsUtility.isHorizontalMovement(originCol, originRow, targetCol, targetRow)) {
//			checkHorizontalPath(originRow,originCol,targetCol);
//		}
//		else if(boardMovementsUtility.isVerticalMovement(originCol, originRow, targetCol, targetRow)) {
//			checkVerticalPath(originCol, originRow, targetRow);
//		}
//		else {
//			checkDiagonalPath(originCol, originRow, targetCol, targetRow);
//		}
		int verticalDirection = boardMovementsUtility.getDirection(originRow, targetRow);
		int horizontalDirection = boardMovementsUtility.getDirection(originCol, targetCol);
		for(char col = originCol, row = originRow; col != targetCol || row != targetRow; col += horizontalDirection, row += verticalDirection) {
			if(occupied(String.format("%c%c",col, row))) {
				throw new InvalidMoveException("Path is not clear");
			}
		}
	}

	private void checkDiagonalPath(char originCol, char originRow, char targetCol, char targetRow) {
		// TODO Auto-generated method stub
		
		
	}

	private void checkVerticalPath(char originCol, char originRow, char targetRow) throws InvalidMoveException {
		// TODO Auto-generated method stub
		int direction = (targetRow - originRow > 0) ? 1 : -1;
		for(char row = originRow; row != targetRow; row+=direction) {
			if(occupied(String.format("%c%c",originCol, row))) {
				throw new InvalidMoveException("Path is not clear");
			}
		}
	}

	private void checkHorizontalPath(char originRow, char originCol, char targetCol) throws InvalidMoveException {
		// TODO Auto-generated method stub
		int direction = (targetCol - originCol > 0) ? 1 : -1;
		for(char col = originCol; col != targetCol; col+=direction) {
			if(occupied(String.format("%c%c", col,originRow))) {
				throw new InvalidMoveException("Path is not clear");
			}
		}
	}

	private void validateTargetPosition(Piece movingPiece, String target) throws InvalidMoveException {
		
		if(!occupied(target)) return;
		Piece capturedPiece = board.pieces.get(target);
		validateCapturedPiece(movingPiece,capturedPiece);
	}

	private void validateCapturedPiece(Piece movingPiece, Piece capturedPiece) throws InvalidMoveException {
	
		if(boardPiecesUtility.sameTeam(movingPiece, capturedPiece)) {
			throw new InvalidMoveException("The piece at Target Position belongs to you");
		}
		
	}

	private void movePiece(String origin, String target) {
		Piece movingPiece = board.pieces.get(origin);
		//changePosition(movingPiece,target);
        board.pieces.put(target, movingPiece);
        board.pieces.remove(origin);
		
	}

	private void validateOriginPosition(String origin) throws InvalidPositionException, InvalidMoveException{
		// TODO Auto-generated method stub
		if(!boardPositionUtility.isOnBoard(origin)) {
			throw new InvalidPositionException("Origin Position is not on board");
		}
		if(!occupied(origin)) {
			throw new InvalidPositionException("Origin Position has no piece to move");
		}
	}

	private void validateMovingPiece(Piece piece) throws InvalidMoveException {
		if(!(piece.getColor().equals(game.getTurn()))) {
			throw new InvalidMoveException("The piece at Origin Position does not belong to you");
		}
	}

	private boolean occupied(String position) {
		return board.pieces.containsKey(position);
	}

	public void checkKingChecked() {
		// TODO Auto-generated method stub

	}

	public void checkCheckMate() {
		// TODO Auto-generated method stub

	}

	public void checkStaleMate() {
		// TODO Auto-generated method stub

	}

}
