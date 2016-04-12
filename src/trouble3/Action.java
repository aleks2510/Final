package trouble3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Action {
	//crates the player object for each player
	public static Player[] createPlayers(){
		Player[] players = new Player[2];
		//System.out.print("Enter three letter nick name of Player1: ");
		players[0] = new Player(getName(), 0);
		//System.out.print("Enter three letter nick name of Player2: ");
		players[1] = new Player(getName(), 18);
		return players;
	}
	//trunkates the name to 3 char
	private static String getName(){
		return JOptionPane.showInputDialog("Enter player name:");
	}
}

class PieceButtonListener implements ActionListener{
	Piece clickedPiece;
	Player player;
	boolean clickable = false;
	public PieceButtonListener(Piece piece){
		clickedPiece = piece;
		player = piece.getOwner();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//if clickable
		if(clickable){
			//check if piece is on the board
			if(clickedPiece.isOnBoard()){
				//move the piece
				player.checkAndMove(Die.getRoll(), clickedPiece);
				//make unclickable
				clickable = false;
			}
			else if(!clickedPiece.isOnBoard()){
				//put the piece on the board
				player.comeOut(clickedPiece);
				//make unclickable
				clickable = false;
			}
		}
		else{
			//update message
			TestGUI.southPanel.message.setText("Roll First");
		}
	}
}

class DieButtonListener implements ActionListener{
	boolean clickable = false;
	int roll;
	@Override
	public void actionPerformed(ActionEvent e){
		//if(clickable){
		//roll the die
		Die.roll();
		roll = Die.getRoll();
		//if(player has available moves)
		//change the message
		TestGUI.southPanel.message.setText("Pick a piece to move by clicking the icon of the piece.");
		//make clickable PieceButtons
		pieceButtonsClickable(true);
		TestGUI.eastPanel.rollPanel.dieButton.clickable = false;
		//display the numberlabel
		ImageIcon number = Die.getIcon();
		TestGUI.eastPanel.rollPanel.rollValueLabel.setIcon(number);
		TestGUI.eastPanel.repaint();
		TestGUI.southPanel.repaint();
		//}
	/*	else {
			
			//write message, not available, msg0
		}*/
	}
	
	void pieceButtonsClickable(boolean state){
		//change the curentplayers pieceButtons to clickable
		
	}
	
	void rollButtonClickable(boolean state){
		//change the curentplayers pieceButtons to clickable
		
	}
}

class Turn {
	static int turnCounter = 0;
	static Player currentPlayer;
	static SidePanel currentPlayerPanel;
	
	static public void update(){
		//set new currentPlayer
				currentPlayer = TestGUI.players[turnCounter % 2];
				if(turnCounter % 2 == 0){
					currentPlayerPanel = TestGUI.eastPanel;
				}
				else{
					currentPlayerPanel = TestGUI.westPanel;
				}
	}
	
	static public void nextTurn(){
		//increment the turnCounter
		if(Die.getRoll() != 6)
			turnCounter++;
		update();
		//move the RollPanel to the next players side
		/*if(turnCounter % 2 == 0){
			//remove from current player
			TestGUI.eastPanel.rollPanel.setVisible(false);
			TestGUI.eastPanel.repaint();
			//add to the next player
			TestGUI.westPanel.rollPanel.setVisible(false);
			TestGUI.westPanel.repaint();
		}
		else{
			//remove from current player
			TestGUI.westPanel.rollPanel.setVisible(false);
			TestGUI.westPanel.repaint();
			//add to the next player
			TestGUI.eastPanel.rollPanel.setVisible(false);
			TestGUI.eastPanel.repaint();
		}*/
		//end game display
		
	}
	
	static public boolean winCheck(){
		if(currentPlayer.hasWon())
			return true;
		return false;
	}
	
}