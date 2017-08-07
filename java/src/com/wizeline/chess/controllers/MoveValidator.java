package com.wizeline.chess.controllers;

import java.util.HashMap;

import com.wizeline.chess.Board;
import com.wizeline.chess.exceptions.InvalidMoveException;
import com.wizeline.chess.models.King;
import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.models.Rook;
import com.wizeline.chess.utilities.BoardMovementsUtility;
import com.wizeline.chess.utilities.BoardPiecesUtility;
import com.wizeline.chess.utilities.BoardPositionUtility;

public class MoveValidator {

	public static final boolean NORMAL_MOVE = false;
	public static final boolean SPECIAL_MOVE = true;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	private HashMap<String, Piece> board;
	private BoardPositionUtility boardPositionUtility;
	private BoardPiecesUtility boardPiecesUtility;
	private BoardMovementsUtility boardMovementsUtility;

	public MoveValidator(HashMap<String, Piece> boardCopy) {
		// this.board = board;
		this.board = boardCopy;
		// this.game = game;
		boardPositionUtility = new BoardPositionUtility();
		boardPiecesUtility = new BoardPiecesUtility();
		boardMovementsUtility = new BoardMovementsUtility();
	}

	public void validatePath(Piece movingPiece, char originCol, char originRow, char targetCol, char targetRow)
			throws InvalidMoveException {
		if (movingPiece.canBeBlocked() && !pathIsClear(originCol, originRow, targetCol, targetRow)) {
			throw new InvalidMoveException("");
		}

	}

	private boolean isThereAnyPieceAlongThePath(char originCol, char originRow, char targetCol, char targetRow) {
		int verticalDirection = boardMovementsUtility.getDirection(originRow, targetRow);
		int horizontalDirection = boardMovementsUtility.getDirection(originCol, targetCol);
		originCol += horizontalDirection;
		originRow += verticalDirection;
		for (char col = originCol, row = originRow; col != targetCol
				|| row != targetRow; col += horizontalDirection, row += verticalDirection) {
			if (occupied(String.format("%c%c", col, row))) {
				return true;
			}
		}
		return false;
	}

	private boolean canCapture(Piece capturingPiece, String origin, String target) {
		char originCol = origin.charAt(0);
		char originRow = origin.charAt(1);
		char targetCol = target.charAt(0);
		char targetRow = target.charAt(1);
		return isValidPseudoMove(capturingPiece, origin, target, originCol, originRow, targetCol, targetRow);
	}

	public boolean occupied(String position) {
		return board.get(position) != null;
	}

	public boolean isValidCastlingMove(King king, Rook rook, char originCol, char originRow, char targetCol,
			char targetRow, String rookPosition) {
		return (rook.canCastle() && isCastlingLikeMove(originCol, originRow, targetCol, targetRow, rookPosition));
	}

	private boolean isCastlingLikeMove(char originCol, char originRow, char targetCol, char targetRow,
			String rookPosition) {

		char rookCol = rookPosition.charAt(0);
		char rookRow = rookPosition.charAt(1);
		return boardMovementsUtility.isCastlingLikeMovement(originCol, originRow, targetCol, targetRow)
				&& pathIsClear(originCol, originRow, rookCol, rookRow);
	}

	public boolean pathIsClear(char originCol, char originRow, char targetCol, char targetRow) {

		return !isThereAnyPieceAlongThePath(originCol, originRow, targetCol, targetRow);
	}

	public boolean isValidPseudoMove(Piece piece, String origin, String target, char originCol, char originRow,
			char targetCol, char targetRow) {
		return (piece.canMove(originCol, originRow, targetCol, targetRow, NORMAL_MOVE))
				&& !(piece.canBeBlocked() && !pathIsClear(originCol, originRow, targetCol, targetRow))
				&& !(occupied(target) && boardPiecesUtility.sameTeam(piece, board.get(target)));
	}

	public boolean isEnPassantSituation(Pawn pawn, String origin, String target) {

		if (!isOnFifthRank(pawn, origin))
			return false;
		String possibleOpponentPawnPosition = boardPositionUtility.getCornerSquare(origin, target);
		Piece opponent = board.get(possibleOpponentPawnPosition);
		return (opponent != null && opponent instanceof Pawn && !boardPiecesUtility.sameTeam(pawn, opponent)
				&& ((Pawn) opponent).isEnPassant());
	}

	public void undoMove(String origin, String target, Piece movingPiece, Piece mayBeCaptured,
			String enPassantPosition) {
		if (movingPiece != null) {
			movePiece(target, origin);
		}
		if (mayBeCaptured != null) {
			if (enPassantPosition != null) {
				board.put(enPassantPosition, mayBeCaptured);
			} else {
				board.put(target, mayBeCaptured);
			}

		}

	}

	public void movePiece(String origin, String target) {
		Piece piece = board.get(origin);
		board.put(target, piece);
		board.remove(origin);

	}

	public boolean isValidPawnCaptureMovement(Pawn pawn, String origin, String target, char originRow, char originCol,
			char targetRow, char targetCol) {

		if (!boardMovementsUtility.isPawnCaptureMovement(originCol, originRow, targetCol, targetRow,
				pawn.getColor().equals(Board.WHITE_PIECE) ? 1 : -1)) {
			return false;
		}
		return normalPawnCapture(pawn, origin, target) || isEnPassantSituation(pawn, origin, target);
	}

	private boolean normalPawnCapture(Pawn pawn, String origin, String target) {
		return occupied(target) && !boardPiecesUtility.sameTeam(pawn, board.get(target));
	}

	private boolean isOnFifthRank(Pawn pawn, String origin) {
		return (pawn.getColor().equals(Board.WHITE_PIECE)) ? origin.charAt(1) == '5' : origin.charAt(1) == '4';
	}

	public String getCurrentKingPosition(String turn) {
		return board.keySet().stream().filter(k -> {
			Piece piece = board.get(k);
			return piece instanceof King && piece.getColor().equals(turn);
		}).findAny().orElse(null);
	}

	public boolean isCurrentKingOnCheck(String color) {
		String kingPosition = getCurrentKingPosition(color);
		return board.entrySet().stream().anyMatch(e -> canCapture(e.getValue(), e.getKey(), kingPosition));
	}

	public Rook getRookToCastle(String rookPosition) {
		Piece piece = board.get(rookPosition);
		return (piece != null && piece instanceof Rook) ? (Rook) piece : null;
	}

	public String getRookPositionToCastle(King king, boolean rightOrLeft) {
		if (king.getColor().equals(Board.WHITE_PIECE)) {
			return Board.WHITE_CORNERS[rightOrLeft ? RIGHT : LEFT];
		} else {
			return Board.BLACK_CORNERS[rightOrLeft ? RIGHT : LEFT];
		}
	}

	public boolean canMakeCastling(King king, Rook rook, char originCol, char originRow, char targetCol, char targetRow,
			String rookPosition) {
		return this.isValidCastlingMove(king, rook, originCol, originRow, targetCol, targetRow, rookPosition);
	}

	public void castle(String origin, String target, String rookPosition, String newRookPosition) {

		Piece piece = board.get(origin);
		board.put(target, piece);
		board.remove(origin);
		piece = board.get(rookPosition);
		board.put(newRookPosition, piece);
		board.remove(rookPosition);
	}

	public void undoCastle(String origin, String target, String rookPosition, String newRookPosition) {
		castle(target, origin, newRookPosition, rookPosition);

	}

	public boolean isEnPassantMovement(String target, char originCol, char originRow, char targetCol, char targetRow) {
		return boardMovementsUtility.isSingleMoveDiagonal(originCol, originRow, targetCol, targetRow)
				&& !occupied(target);

	}

	public void makeCastling(String origin, String target) {
		boolean rightOrLeft = boardPositionUtility.toTheRight(target.charAt(0), origin.charAt(0));
		String rookPosition = getRookPositionToCastle((King) board.get(origin), rightOrLeft);
		String newRookPosition = boardPositionUtility.getCastlingRookPosition(target, rightOrLeft);
		castle(origin, target, rookPosition, newRookPosition);
	}
}
