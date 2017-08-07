package com.wizeline.chess.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.wizeline.chess.Board;
import com.wizeline.chess.models.Game;
import com.wizeline.chess.models.King;
import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.models.Rook;
import com.wizeline.chess.utilities.BoardMovementsUtility;
import com.wizeline.chess.utilities.BoardPiecesUtility;
import com.wizeline.chess.utilities.BoardPositionUtility;

public class MoveGenerator {
	private MoveValidator moveValidator;
	private BoardMovementsUtility boardMovementsUtility;
	private BoardPositionUtility boardPositionUtility;
	private BoardPiecesUtility boardPiecesUtility;
	private Board board;
	private Game game;

	public MoveGenerator(Board board, Game game) {
		this.board = board;
		this.boardMovementsUtility = new BoardMovementsUtility();
		this.boardPositionUtility = new BoardPositionUtility();
		this.boardPiecesUtility = new BoardPiecesUtility();
		this.game = game;
	}

	public HashSet<String> generatePseudoMoves(Piece piece, String origin) {
		HashSet<String> moves = new HashSet<>();
		for (int row = 0; row < Board.ROWS; row++) {
			for (int col = 0; col < Board.COLS; col++) {
				String possibleTarget = board.getChessNotation(row, col);
				char originCol = origin.charAt(0);
				char originRow = origin.charAt(1);
				char targetCol = possibleTarget.charAt(0);
				char targetRow = possibleTarget.charAt(1);
				if (moveValidator.isValidPseudoMove(piece, origin, possibleTarget, originCol, originRow, targetCol,
						targetRow)) {
					moves.add(possibleTarget);
				} else if (piece instanceof Pawn && !((Pawn) piece).isPromoted()
						&& piece.canMove(originCol, originRow, targetCol, targetRow, MoveValidator.SPECIAL_MOVE)) {
					Pawn pawn = (Pawn) piece;
					if (moveValidator.isValidPawnCaptureMovement(pawn, origin, possibleTarget, originRow, originCol,
							targetRow, targetCol)) {
						moves.add(possibleTarget);
					}
				} else if (piece instanceof King
						&& piece.canMove(originCol, originRow, targetCol, targetRow, MoveValidator.SPECIAL_MOVE)) {
					King king = (King) piece;
					boolean rightOrLeft = boardPositionUtility.toTheRight(targetCol, originCol);
					String rookPosition = moveValidator.getRookPositionToCastle(king, rightOrLeft);
					Rook rook = moveValidator.getRookToCastle(rookPosition);
					// String newRookPosition =
					// boardPositionUtility.getCastlingRookPosition(possibleTarget,
					// rightOrLeft);
					if (rook != null && moveValidator.canMakeCastling(king, rook, originCol, originRow, targetCol,
							targetRow, rookPosition)) {
						moves.add(possibleTarget);
					}

				}

			}
		}
		return moves;
	}

	private HashSet<String> generateLegalMoves(HashMap<String, Piece> boardCopy, Piece piece, String origin) {
		HashSet<String> moves = generatePseudoMoves(piece, origin);
		HashSet<String> legalMoves = new HashSet<>(moves);
		for (Iterator<String> i = moves.iterator(); i.hasNext();) {
			String target = i.next();
			char originCol = origin.charAt(0);
			char originRow = origin.charAt(1);
			char targetCol = target.charAt(0);
			char targetRow = target.charAt(1);
			Piece capturedPiece = board.getPiece(target);
			String capturedPiecePosition = target;
			if (piece instanceof King
					&& boardMovementsUtility.isCastlingLikeMovement(originCol, originRow, targetCol, targetRow)) {
				boolean rightOrLeft = boardPositionUtility.toTheRight(targetCol, originCol);
				String rookPosition = moveValidator.getRookPositionToCastle((King) piece, rightOrLeft);
				String newRookPosition = boardPositionUtility.getCastlingRookPosition(target, rightOrLeft);
				moveValidator.castle(origin, target, rookPosition, newRookPosition);
				if (moveValidator.isCurrentKingOnCheck(piece.getColor())) {
					legalMoves.remove(target);
				}
				moveValidator.undoCastle(origin, target, rookPosition, newRookPosition);
			} else {

				if (piece instanceof Pawn
						&& moveValidator.isEnPassantMovement(target, originCol, originRow, targetCol, targetRow)) {
					String opponentEnPassantPawnPosition = boardPositionUtility.getCornerSquare(origin, target);
					capturedPiece = boardCopy.get(opponentEnPassantPawnPosition);
					capturedPiecePosition = opponentEnPassantPawnPosition;
				}
				boardCopy.remove(capturedPiecePosition);
				moveValidator.movePiece(origin, target);
				if (moveValidator.isCurrentKingOnCheck(piece.getColor())) {
					legalMoves.remove(target);
				}

				moveValidator.movePiece(target, origin);
				// piece = boardCopy.get(target);
				// boardCopy.put(origin, piece);
				// boardCopy.remove(target);
				if (capturedPiece != null) {
					boardCopy.put(capturedPiecePosition, capturedPiece);
				}
			}
		}

		return legalMoves;
	}

	public HashMap<String, HashSet<String>> generateAllPossibleMoves(HashMap<String, Piece> boardCopy, String player) {
		this.moveValidator = new MoveValidator(boardCopy);
		HashMap<String, HashSet<String>> allMoves = new HashMap<>();
		HashSet<String> currentPositions = (HashSet<String>) board.pieces.keySet().stream()
				.filter(k -> board.getPiece(k).getColor().equals(player)).collect(Collectors.toSet());
		for (String position : currentPositions) {
			String origin = position;
			Piece piece = board.getPiece(origin);
			HashSet<String> moves = generateLegalMoves(boardCopy, piece, origin);
			if (!moves.isEmpty()) {
				allMoves.put(origin, moves);
			}
		}
		// suggest valid moves
		System.out.println("Valid Moves : " + allMoves);
		return allMoves;
	}
}