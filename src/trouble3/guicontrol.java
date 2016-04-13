package trouble3;

import java.awt.event.*;

import javax.swing.*;
// This class will create a panel in which we will control our GUI. This will make testing the game easier.
//It will contain two different Buttons to change the player and to put the # die.

public class guicontrol extends JFrame {
	public guicontrol() {
		JButton spanel = new JButton("switch panel");
		JButton dienumber = new JButton("put # die");
		JButton onbord = new JButton("onbord");
		JButton rulesButton = new JButton("Rules");

		JPanel panel = new JPanel();
		panel.add(spanel);
		panel.add(dienumber);
		add(panel);
		panel.add(rulesButton);

		// Here we are creating an action Listener for the "spanel" button which
		// it will change the turn of the game.
		spanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Turn.nextTurn();
			}
		});
		// Here we are creating an action listener for the rulesButton which it
		// will display the instructions for the game
		rulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame rulesFrame = new JFrame();
				JPanel rulesPanel = new JPanel();
				JLabel myLabel = new JLabel();
				myLabel.setText("<html><p>Welcome to Star Trouble Game<br /> 1. Who goes first: Players roll the die, the player that rolls the first six will make the first move.<br />2. Movement: players must roll a 6 to move one of their 2 pieces on to the starting area.<br />3. Conditions:<br />Players cannot have more than one piece on the same location at a time.<br />Landing on a space occupied by another player's piece will bump the other player's piece home.<br />If a 6 is rolled the player gets to roll again.<br />Winner: The winner will be the player who gets both pieces into the finished area.</p></html>");
				rulesPanel.add(myLabel);
				rulesFrame.add(rulesPanel);
				rulesFrame.setSize(620, 200);
				rulesFrame.setLocationRelativeTo(null);
				rulesFrame.setVisible(true);

			}
		});
		// Here we are creating an action listener to the "dienumber" button so
		// the die it will be 6.
		dienumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Die.roll = 6;
			}
		});

		onbord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Turn.currentPlayer.comeOut(Turn.currentPlayer.piece[0]);
			}
		});

	}
}