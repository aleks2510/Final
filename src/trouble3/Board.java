package trouble3;

public class Board {
	//length of the board
	static int BOARD_LENGTH = 36;
	//the amount of spaces per side
	static int BOARD_SIZE = 10;
	//the actual board(an array of no arg pieces)
	static Piece[] Spaces = new Piece[BOARD_LENGTH];
	//crates an empty board of pieces
	public static void blankBoard() {
		for (int i = 0; i < Spaces.length; i++) {
			Spaces[i] = new Piece();
		}
	}
	//a winning display for the player who takes the game
	public static void winnerBoard(Player winner) {
		for (int i = 0; i < Spaces.length; i++) {
			Spaces[i] = winner.piece[0];
		}
	}
	//clears the designated space on the board
	public static void blankSpace(int index) {
		Spaces[index] = new Piece();
	}
	//displays the board on the console
	public static void printBoard(Player[] players) {
		int startCount = 0;
		int endCount = BOARD_LENGTH - 1;
		//top row
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(Spaces[startCount++].boardID);
		}
		System.out.println();
		//sides
		for (int i = 0; i < BOARD_SIZE - 2; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (j == 0) {
					System.out.print(Spaces[endCount--].boardID);
				} else if (j == BOARD_SIZE - 1) {
					System.out.println(Spaces[startCount++].boardID);
				} else {
					System.out.print("      ");
				}
			}
		}
		//bottom row
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(Spaces[endCount--].boardID);
		}
		//player information
		System.out.println();
		System.out.println();
		players[0].showPlayerInfo();
		players[1].showPlayerInfo();
		System.out.println();
		System.out.println();
	}
}