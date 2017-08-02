package com.wizeline.chess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
The Window class handles the User Interface. It is divided in two sections:
- The chess board which is handle by the Board class.
- The input and output, in which the user can input text to make the movements of the 
  pieces. Text can also be displayed to show messages for the players.
  
To handle the input from the input text field (when the Enter/Return key is pressed or
the Submit button is clicked) modify the actionPerformed method from the private class InputActionListener.
 
To display information such as whose turn it is, if a player is in check, error messages and more
use the JLabel outputLabel. To modify its value use its method setText(String).

The Window class contains an instance from the Board class.
The Board class is used to draw the board and the pieces according to the values of its
public HashMap<String, String> variable "pieces" which must contain a key for every
active piece on the board. The key corresponds to a board coordinate (example: a1, f5,
etc.), the value corresponding to that key must be the piece that lives on that square.
A piece is represented by a string composed of the color of the piece (b or w) followed
by the type of the piece. The different piece types are:
K - King
Q - Queen
N - kNight
B - Bishop
R - Rook
P - Pawn

You are expected to ask the user for their next movement (example: a2a4, will move the
piece in a2 to the tile a4), and redraw the board after every successfully state change
on the board. The Board class has a public method draw() that will draw the pieces according
to how they are in the public HashMap<String, String> variable "pieces".

Remember, we'll evaluate both design and implementation, even when implementing many
parts of the game is good, it's better to have a solid design.

NOTE: Make sure that the images folder is inside the src folder or at the same path than the
com directory

Some rules to remember:
    * General rules of chess (https://en.wikipedia.org/wiki/Rules_of_chess)
    * Castiling (http://en.wikipedia.org/wiki/Castling)
    * En Passant (http://en.wikipedia.org/wiki/En_passant)
    * If you're on check the only valid moves are those that let you on a no-check state
    * If a move lets you on a check state that move is invalid
    * If the current player is not on check but has no valid moves available the game
      ends as a draw
    * If the current player is on check and has no way to remove its status a checkmate
      is declared
    * Every time a player puts its opponent on check the event must be declared
    * A pawn becomes a queen if they get to the opponent's first row
*/

public class Window {
    private Board board;
    private JPanel bottomPanel;
    private JButton submitButton;
    private JFrame frame;
    
    public JLabel outputLabel;
    public JTextField textField;
    
    public Window() {
        initializeWindow();
        
        board = new Board();
        //TODO: Add the pieces to the board
        board.pieces.put("a8", "bR");
        board.pieces.put("b8", "bN");
        board.pieces.put("c8", "bB");
        board.pieces.put("d7", "bP");
        board.pieces.put("e1", "wK");
        frame.add(board, BorderLayout.CENTER);
        
        initializeGraphicalComponents();
        displayWindow();
    }

    private void initializeWindow() {
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void initializeGraphicalComponents() {
        // Creating the bottom panel that will hold the 
        // output text label, input text and submit button
        bottomPanel = new JPanel(new BorderLayout());
        outputLabel = new JLabel("Output text in this label", SwingConstants.CENTER);
        bottomPanel.add(outputLabel, BorderLayout.NORTH);
        
        InputActionListener inputListener = new InputActionListener();
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Input: ");
        textField = new JTextField(30);
        textField.addActionListener(inputListener);
        inputPanel.add(inputLabel);
        inputPanel.add(textField);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(inputListener);
        bottomPanel.add(submitButton, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void displayWindow() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
    private class InputActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Text is read from the input field and input field is cleared
            String input = textField.getText();
            textField.setText("");
            
            //TODO: Process input read from text field and redraw the board
        }
    }
}
