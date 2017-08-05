package com.wizeline.chess.models;

import javax.swing.JLabel;

public class Game {
   public static final String WHITE_TURN = "w";
   public static final String BLACK_TURN = "b";
   
   private String turn;
   private Player whitePlayer;
   private Player blackPlayer;
   
   public Game() {
	   whitePlayer = new Player();
	   blackPlayer = new Player();
	   this.turn = WHITE_TURN;
   }

public String getTurn() {
	return turn;
}

public void setTurn(String turn) {
	this.turn = turn;
}
   
public void changeTurn(JLabel outputLabel) {
	turn = (turn.equals(WHITE_TURN)) ? BLACK_TURN : WHITE_TURN;
	
	showWhoseTurnOnLabel(outputLabel);
}

private void showWhoseTurnOnLabel(JLabel outputLabel) {
	if(turn.equals(WHITE_TURN)) {
		outputLabel.setText("White Player's turn");
	}
	else {
		outputLabel.setText("Black Player's turn");
	}
	
}

}
