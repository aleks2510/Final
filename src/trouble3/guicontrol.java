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
		
		JPanel panel = new JPanel();
		panel.add(spanel);
		panel.add(dienumber);
		add(panel);
		spanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Turn.nextTurn();
			}
		});
		dienumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Die.roll = 6 ; 
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