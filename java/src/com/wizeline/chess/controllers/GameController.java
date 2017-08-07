package com.wizeline.chess.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.GameControllerException;
import com.wizeline.chess.exceptions.InvalidColorException;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.exceptions.InvalidPositionException;
import com.wizeline.chess.models.Game;
import com.wizeline.chess.models.King;
import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.utilities.BoardMovementsUtility;
import com.wizeline.chess.utilities.BoardPiecesUtility;
import com.wizeline.chess.utilities.BoardPositionUtility;
import com.wizeline.chess.validators.QueenMovement;

public class GameController {

	private static final String WHITE_PLAYER_TURN = "White Player's turn";
	private static final String BLACK_PLAYER_TURN = "Black Player's turn";
	private static final String GAME_OVER = "Game Over";

	private Board board;
	private PieceFactory pieceFactory;
	private Game game;
	private String message;
	private BoardPositionUtility boardPositionUtility;
	private BoardMovementsUtility boardMovementsUtility;
	private BoardPiecesUtility boardPiecesUtility;
	private MoveValidator moveValidator;
	public MoveGenerator moveGenerator;
	private HashMap<String, Piece> tempBoardCopy;
	private HashMap<String, HashSet<String>> currentPossibleMoves;
	private Stack<String> movesToUndo;
	private Stack<String> movesToRedo;

	public GameController() {

		game = new Game();
		this.boardPositionUtility = new BoardPositionUtility();
		this.boardMovementsUtility = new BoardMovementsUtility();
		this.boardPiecesUtility = new BoardPiecesUtility();
		currentPossibleMoves = new HashMap<>();
		this.message = WHITE_PLAYER_TURN;
	}

	public void initializeBoard() throws InvalidColorException, InvalidPositionException {
		this.board = new Board();
		this.pieceFactory = new PieceFactory(board);
		this.moveGenerator = new MoveGenerator(this.board, this.game);
		pieceFactory.initializePieces();
		this.tempBoardCopy = new HashMap<>(this.board.pieces);
		this.moveValidator = new MoveValidator(this.board.pieces);
		currentPossibleMoves = moveGenerator.generateAllPossibleMoves(this.tempBoardCopy, game.getTurn());

	}

	public Board getBoard() {
		return board;
	}

	public void makeMove(String origin, String target) throws GameControllerException {
		if (game.getStatus() == Game.OVER || currentPossibleMoves.get(origin) == null
				|| !currentPossibleMoves.get(origin).contains(target)) {
			throw new InvalidMoveException("");
		}

		Piece movingPiece = board.getPiece(origin);
		// Piece mayBeCaptured = board.getPiece(target);
		char originCol = origin.charAt(0);
		char originRow = origin.charAt(1);
		char targetCol = target.charAt(0);
		char targetRow = target.charAt(1);
		if (movingPiece instanceof King
				&& boardMovementsUtility.isCastlingLikeMovement(originCol, originRow, targetCol, targetRow)) {
			moveValidator.makeCastling(origin, target);
		} else if (moveValidator.isEnPassantMovement(target, originCol, originRow, targetCol, targetRow)) {
			String enPassantPosition = boardPositionUtility.getCornerSquare(origin, target);
			board.pieces.remove(enPassantPosition);
			board.movePiece(origin, target);
		} else {
			board.movePiece(origin, target);
		}

		movingPiece.move();
		switchTurn();
		updateGameStatus(movingPiece, origin, target);
		// movesToUndo.push(target+origin);
	}

	private void updateGameStatus(Piece movingPiece, String origin, String target) {
		// TODO Auto-generated method stub
		if (movingPiece instanceof Pawn) {
			updatePawnStatus((Pawn) movingPiece, origin, target);
		}
		this.tempBoardCopy = new HashMap<String, Piece>(this.board.pieces);
		currentPossibleMoves = moveGenerator.generateAllPossibleMoves(this.tempBoardCopy, game.getTurn());
		boolean currentKingOnCheck = moveValidator.isCurrentKingOnCheck(game.getTurn());
		if (currentKingOnCheck) {
			declareCheckEvent();
		}
		if (checkMate(currentKingOnCheck)) {
			game.end(Game.OVER);
		} else if (staleMate(currentKingOnCheck)) {
			game.end(Game.DRAW);
		}
		if (!currentKingOnCheck) {
			defaultSetMessage();
		}

	}

	private void defaultSetMessage() {
		// TODO Auto-generated method stub
		if (game.getStatus() == Game.ACTIVE) {
			setMessage((game.getTurn().equals(Game.WHITE_TURN)) ? WHITE_PLAYER_TURN : BLACK_PLAYER_TURN);
		} else if (game.getStatus() == Game.OVER) {
			setMessage(
					GAME_OVER + (game.getWinner().equals(Game.WHITE_TURN) ? " White Player won" : "Black Player won"));
		} else {
			setMessage(GAME_OVER + " Draw");
		}
	}

	private void declareCheckEvent() {
		String player = game.getTurn().equals(Game.WHITE_TURN) ? "White Player " : "Black Player ";
		setMessage(player + "on Check");

	}

	private void setMessage(String s) {
		this.message = s;

	}

	private void updatePawnStatus(Pawn pawn, String origin, String target) {
		if (boardMovementsUtility.isDoubleMoveForward(origin, target)) {
			pawn.markEnPassant();
		}
		if (isEligibleToPromote(pawn, target)) {
			boardPiecesUtility.promote(pawn, new QueenMovement());
		}
	}

	private boolean isEligibleToPromote(Piece piece, String position) {
		// TODO Auto-generated method stub
		char rank = position.charAt(1);
		return (piece.getColor().equals(Board.WHITE_PIECE)) ? rank == Board.INITIAL_BLACK_BACK_ROW
				: rank == Board.INITIAL_WHITE_BACK_ROW;
	}

	private void switchTurn() {
		game.switchTurn();
		terminateCurrentEnPassant();
	}

	private boolean checkMate(boolean currentKingOnCheck) {
		return currentKingOnCheck && noValidMoves();
	}

	private boolean staleMate(boolean currentKingOnCheck) {
		return !currentKingOnCheck && noValidMoves();

	}

	private boolean noValidMoves() {
		return currentPossibleMoves.isEmpty();
	}

	private void terminateCurrentEnPassant() {
		String row = (game.getTurn().equals(Game.WHITE_TURN)) ? "4" : "5";
		for (String col : Board.COLUMN_NAMES) {
			String position = col + row;
			Piece piece = board.getPiece(position);
			if (piece != null && piece instanceof Pawn) {
				Pawn pawn = (Pawn) piece;
				pawn.unMarkEnPassant();
			}
		}
	}

	// TODO: Complete Undo Function
	public void undo() {
		if (!movesToUndo.isEmpty()) {
			String move = movesToUndo.pop();
			String origin = move.substring(0, 2);
			String target = move.substring(2, 4);

			movesToRedo.push(target + origin);
		}

	}

	public Game getGame() {
		return this.game;
	}

	public String getMessage() {

		return this.message;
	}

}
