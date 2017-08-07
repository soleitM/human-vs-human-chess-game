package com.wizeline.chess.utilities;

import com.wizeline.chess.models.Pawn;
import com.wizeline.chess.models.Piece;
import com.wizeline.chess.models.Queen;
import com.wizeline.chess.validators.MovementValidator;
import com.wizeline.chess.validators.QueenMovement;

public class BoardPiecesUtility {
   public boolean sameTeam (Piece movingPiece, Piece capturedPiece) {
	   return movingPiece.getColor().equals(capturedPiece.getColor());
   }

	public void promote(Pawn promotedPawn, MovementValidator movementValidator) {
	   promotedPawn.setMovementValidator(movementValidator);
	   promotedPawn.makePromoted();
   }
   
   
}
