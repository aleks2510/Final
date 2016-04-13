package trouble3;

import javax.swing.*;

//This class will create a Die and it will generate a number for the result 
//of rolling the dice. The result will generate a random number between 1-6
public class Die {
	// Setting the class variables.
	static boolean hasRolled = false;
	static int roll = 0;

	public Die() {
		// Creating the object

	}

	static public void roll() {
		roll = (int) (Math.random() * 6) + 1;
		if (roll == 6)
			TestGUI.southPanel.message.setIcon(new ImageIcon(
					"Assets/Msg/msg4.png"));
	}

	static public int getRoll() {
		return roll;
	}

	// This method will assign a imageIcon to the die number.
	public static ImageIcon getIcon() {
		ImageIcon number = new ImageIcon("Assets/Numbers/" + roll + ".png");
		// clear the last message
		Turn.currentPlayerPanel.rollPanel.rollValueLabel.setText(" ");
		return number;// the image of the number
	}

	public static ImageIcon clearIcon() {
		return new ImageIcon("Assets/Numbers/0.png");// the image of the number
	}

}
