package trouble3;

public class Checks {
	// Checks if the destination of a move is empty
	static public boolean destinationFree(int destination) {
		if (Board.Spaces[destination].owner == null) {
			return true;
		}
		return false;
	}

	// Returns the value of the occupying player
	static public Player whoIsThere(int destination) {
		return Board.Spaces[destination].owner;
	}

	// Returns how many pieces the current player has on the board
	static public int onBoard(Player player) {
		int count = 0;
		for (int i = 0; i < player.piece.length; i++) {
			if (player.piece[i].isOnBoard() == true) {
				count++;
			}
		}
		return count;
	}

	// This is used to loop around the board at the end of the array
	public static int destinationCheck(int destination) {
		if (destination > 35)
			return destination - 35;
		return destination;
	}

	// Create a available moves method to check whether the current can make a
	// move with his current roll.
	// if not Action.nextTurn();
	public static boolean availableMoves() {
		Player player = Turn.currentPlayer;
		int onBoard = onBoard(player);
		int roll = Die.getRoll();

		if (onBoard == 0 && roll == 6) {
			// Destination free
			if (destinationFree(player.startPosition)) {
				System.out.print("Can come out1");
				return true;
			}
			// Who is there
			else if (whoIsThere(player.startPosition) != player) {
				// if its not the current player they can move.
				System.out.print("Can come out2");
				return true;
			}
		} else if (onBoard > 0) {
			// Check destination
			int[] destination = new int[player.piece.length];
			for (int i = 0; i < player.piece.length; i++) {
				destination[i] = destinationCheck(Die.getRoll()
						+ player.piece[i].getIndex());
			}
			// Destination free
			for (int i = 0; i < destination.length; i++) {
				if (destinationFree(destination[i])) {
					System.out.print("Can move3");
					return true;
				}
				// Who is there
				else if (whoIsThere(destination[i]) != player) {
					// If its not the current player they can move.
					System.out.print("Can move4");
					return true;
				}
			}
		}
		// Set the text of the rollLabel to show what your roll was
		Turn.currentPlayerPanel.rollPanel.rollValueLabel
				.setText("Your Roll was: " + roll);
		return false;
	}
}
