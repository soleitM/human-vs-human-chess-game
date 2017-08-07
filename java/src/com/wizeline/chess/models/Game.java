package com.wizeline.chess.models;

public class Game {
	public static final String WHITE_TURN = "w";
	public static final String BLACK_TURN = "b";
	public static final int ACTIVE = 1;
	public static final int DRAW = 0;
	public static final int OVER = 2;

	private String turn;
	private int status;

	public Game() {

		setStatus(ACTIVE);
		setTurn(WHITE_TURN);
	}

	public String getTurn() {
		return turn;
	}

	private void setTurn(String turn) {
		this.turn = turn;
	}

	public void switchTurn() {
		setTurn(turn.equals(WHITE_TURN) ? BLACK_TURN : WHITE_TURN);
	}

	public String nextTurn() {
		return (turn.equals(WHITE_TURN)) ? BLACK_TURN : WHITE_TURN;
	}

	public int getStatus() {
		return status;
	}

	private void setStatus(int status) {
		this.status = status;
	}

	public String getWinner() {
		return status == OVER ? nextTurn() : null;
	}

	public void end(int status) {
		setStatus(status);
	}

}
