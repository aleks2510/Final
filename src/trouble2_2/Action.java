package trouble2_2;
import java.util.Scanner;
public class Action {
	static Scanner input = new Scanner(System.in);
	//crates the player object for each player
	public static Player[] createPlayers(){
		Player[] players = new Player[2];
		System.out.print("Enter three letter nick name of Player1: ");
		players[0] = new Player(getName(), 0);
		System.out.print("Enter three letter nick name of Player2: ");
		players[1] = new Player(getName(), 18);
		return players;
	}
	//trunkates the name to 3 char
	private static String getName(){
		return new StringBuilder(input.next()).substring(0,3);
	}
	//ensures the input is a number
	public static int getNumber(){
		int number=0;
		while(true){
			String value = "" + input.next().charAt(0);
			if(Character.isDigit(value.charAt(0))){
			number = Integer.parseInt(value);
			break;
			}
			System.out.println("Incorrect input! try again.");
		}
		return number;
	}
}
