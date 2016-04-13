package trouble3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Action {
	// Creating the player object for each player
	public static Player[] createPlayers() {
		Player[] players = new Player[2];
		// System.out.print("Enter three letter nick name of Player1: ");
		players[0] = new Player(getName(), 18);
		// System.out.print("Enter three letter nick name of Player2: ");
		players[1] = new Player(getName(), 0);
		return players;
	}

	// Truncates the name to 3 char
	private static String getName() {
		return JOptionPane.showInputDialog("Enter player name:");
	}

	public static void pieceButtonsClickable(boolean state) {
		// Change the curentplayers pieceButtons to clickable
		for (int i = 0; i < Turn.currentPlayerPanel.playerPanel.peices.pieceButton.length; i++) {
			Turn.currentPlayerPanel.playerPanel.peices.pieceButton[i].clickable = state;
		}
	}

	public static void rollButtonClickable(boolean state) {
		// Change the curentplayers pieceButtons to clickable
		Turn.currentPlayerPanel.rollPanel.dieButton.clickable = state;
	}

}

class PieceButtonListener implements ActionListener {
	Piece clickedPiece;
	Player player;

	public PieceButtonListener(Piece piece) {
		clickedPiece = piece;
		player = piece.getOwner();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If clickable
		if (Turn.currentPlayerPanel.playerPanel.peices.pieceButton[0].clickable
				|| Turn.currentPlayerPanel.playerPanel.peices.pieceButton[1].clickable) {
			if (!clickedPiece.isFinished()) { // the piece is unable to move
												// either if one finishes.
				// Move the piece
				if (Turn.currentPlayer == player) {
					player.checkAndMove(Die.getRoll(), clickedPiece,
							(IconButton) e.getSource());
					// Put the piece on the board
					player.checkAndComeOut(Die.getRoll(), clickedPiece);
				} else {
					System.out.println("ITS NOT YOUR TURN!");
				}
			} else {
				// Update message
				System.out.println("That Piece is done already!");
			}
		} else {
			TestGUI.southPanel.message.setIcon(new ImageIcon(
					"Assets/Msg/msg0.png"));
		}
	}
}

class DieButtonListener implements ActionListener {
	int roll;

	// The wrong player gets an extra turn when a 6 is rolled.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Turn.currentPlayerPanel.rollPanel.dieButton.clickable) {
			// Roll the die
			Die.roll();
			roll = Die.getRoll();
			System.out.println(roll);
			// Checks if the player can move
			if (Checks.availableMoves()) {
				// Change the message
				TestGUI.southPanel.message.setIcon(new ImageIcon(
						"Assets/Msg/msg3.png"));
				// Make clickable PieceButtons
				Action.pieceButtonsClickable(true);
				// RollButtonClickable(false);
				Action.rollButtonClickable(false);
				// Display the numberlabel
				Turn.currentPlayerPanel.rollPanel.rollValueLabel.setIcon(Die
						.getIcon());
				// Clear last players button
				Turn.currentPlayerPanel.rollPanel.dieButton.setVisible(false);
			} else {
				// Write message, no available moves, msg1, next turn
				TestGUI.southPanel.message.setIcon(new ImageIcon(
						"Assets/Msg/msg1.png"));
				Turn.currentPlayerPanel.rollPanel.dieButton.setVisible(false);
				Turn.nextTurn();
			}
		} else {
			TestGUI.southPanel.message.setIcon(new ImageIcon(
					"Assets/Msg/msg3.png"));
		}
	}
}

class Turn {
	static int turnCounter = 0;
	static Player currentPlayer;
	static SidePanel currentPlayerPanel;

	static void nextTurn() {
		// Increment the turnCounter
		System.out.println("NextTurn.");
		// Update the current player and panel
		update();
		// Go again if you rolled a 6
		if (Die.getRoll() != 6) {
			turnCounter++;
		}
		update();
		// Clear the die label
		Turn.currentPlayerPanel.rollPanel.dieButton.setVisible(true);
		// Update the board
		TestGUI.centerPlayBoard.updateSpaces();
		// Update the southPanel Message
		if (Die.getRoll() != 6)
			TestGUI.southPanel.message.setIcon(new ImageIcon(
					"Assets/Msg/msg0.png"));
		else
			TestGUI.southPanel.message.setIcon(new ImageIcon(
					"Assets/Msg/msg4.png"));
		// Setting buttons clickability
		Action.rollButtonClickable(true);
		Action.pieceButtonsClickable(false);
		// Move the RollPanel to the next players side
		currentPlayerPanel.rollPanel.setVisible(true);
		// End game display
	}

	public static void update() {
		// Setting new currentPlayer
		currentPlayer = TestGUI.players[turnCounter % 2];
		if (turnCounter % 2 == 0) {
			currentPlayerPanel = TestGUI.eastPanel;
		} else {
			currentPlayerPanel = TestGUI.westPanel;
		}
	}

	static public boolean winCheck() {
		if (currentPlayer.hasWon())
			return true;
		return false;
	}

}