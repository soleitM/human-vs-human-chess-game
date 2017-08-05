package com.wizeline.chess.controllers;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Bishop;
import com.wizeline.chess.models.King;
import com.wizeline.chess.models.Knight;
import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Queen;
import com.wizeline.chess.models.Rook;

public class PieceFactory {
	
	private Board board;
	
	public PieceFactory(Board board) {
		this.board = board;
	}
	public void initializePieces() throws InvalidColorException, InvalidPositionException {
		createPawns();
		createRooks();
		createKnights();
		createBishops();
		createQueens();
		createKings();
	}

	public void createPawns() throws InvalidColorException, InvalidPositionException {

		char firstColumn = Board.COLUMN_NAMES[0].charAt(0);
		for (int i = 0; i < 8; i++) {
			Pawn wP = new Pawn(Board.WHITE_PIECE,
					String.format("%c%d", (char) (firstColumn + i), Board.INITIAL_WHITE_FRONT_ROW));
			board.pieces.put(wP.getPosition(), wP);
			Pawn bP = new Pawn(Board.BLACK_PIECE,
					String.format("%c%d", (char) (firstColumn + i), Board.INITIAL_BLACK_FRONT_ROW));
			board.pieces.put(bP.getPosition(), bP);
		}
	}

	private void createBishops() throws InvalidColorException, InvalidPositionException {
		for (String bishopPosition : Board.INITIAL_WHITE_BISHOPS_POSITIONS) {
			board.pieces.put(bishopPosition, new Bishop(Board.WHITE_PIECE, bishopPosition));
		}
		for (String bishopPosition : Board.INITIAL_BLACK_BISHOPS_POSITIONS) {
			board.pieces.put(bishopPosition, new Bishop(Board.BLACK_PIECE, bishopPosition));
		}

	}

	private void createKnights() throws InvalidColorException, InvalidPositionException {
		for (String knightPosition : Board.INITIAL_WHITE_KNIGHTS_POSITIONS) {
			board.pieces.put(knightPosition, new Knight(Board.WHITE_PIECE, knightPosition));
		}
		for (String knightPosition : Board.INITIAL_BLACK__KNIGHTS_POSITIONS) {
			board.pieces.put(knightPosition, new Knight(Board.BLACK_PIECE, knightPosition));
		}
	}

	private void createRooks() throws InvalidColorException, InvalidPositionException {
		for (String rookPosition : Board.WHITE_CORNERS) {
			board.pieces.put(rookPosition, new Rook(Board.WHITE_PIECE, rookPosition));
		}
		for (String rookPosition : Board.BLACK_CORNERS) {
			board.pieces.put(rookPosition, new Rook(Board.BLACK_PIECE, rookPosition));
		}

	}

	private void createKings() throws InvalidColorException, InvalidPositionException {
		board.pieces.put(Board.INITIAL_WHITE_KING_POSITION,
				new King(Board.WHITE_PIECE, Board.INITIAL_WHITE_KING_POSITION));
		board.pieces.put(Board.INITIAL_BLACK_KING_POSITION,
				new King(Board.BLACK_PIECE, Board.INITIAL_BLACK_KING_POSITION));
	}

	private void createQueens() throws InvalidColorException, InvalidPositionException {
		board.pieces.put(Board.INITIAL_WHITE_QUEEN_POSITION,
				new Queen(Board.WHITE_PIECE, Board.INITIAL_WHITE_QUEEN_POSITION));
		board.pieces.put(Board.INITIAL_BLACK_QUEEN_POSITION,
				new Queen(Board.BLACK_PIECE, Board.INITIAL_BLACK_QUEEN_POSITION));
	}
}
