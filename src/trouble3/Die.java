package trouble3;

import javax.swing.*;

public class Die {
	static boolean hasRolled = false;
	static int roll = 0;
	//self explained
	public Die(){
		
	}
	
	static public void roll(){
		roll = (int)(Math.random() * 6)+1;
	}
	
	static public int getRoll(){
		return roll;
	}
	
	public ImageIcon getIcon(){
		ImageIcon number = new ImageIcon("Assets/Numbers/" + roll + ".png");
		return number;//the image of the number
	}
}
