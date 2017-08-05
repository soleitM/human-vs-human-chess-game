package com.wizeline.chess.utilities;

import com.wizeline.chess.models.Piece;

public class BoardPiecesUtility {
   public boolean sameTeam (Piece movingPiece, Piece capturedPiece) {
	   return movingPiece.getColor().equals(capturedPiece.getColor());
   }
}
