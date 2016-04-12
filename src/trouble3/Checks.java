package trouble3;

public class Checks {
	//checks if the destination of a move is empty 
	static public boolean destinationFree(int destination) {
		if (Board.Spaces[destination].owner == null) {
			return true;
		}
		return false;
	}
	//returns the value of the occupying player
	static public Player whoIsThere(int destination) {
		return Board.Spaces[destination].owner;
	}
	//returns how many pieces the current player has on the board
	static public int onBoard(Player player) {
		int count =0;
		for (int i = 0; i < player.piece.length; i++) {
			if (player.piece[i].isOnBoard() == false) {
				count++;
			}
		}
		return count;
	}
	//this is used to loop around the board at the end of the array
	public static int destinationCheck (int destination){
		if (destination > 35)
			return destination - 35;
		return destination;
	}
}
