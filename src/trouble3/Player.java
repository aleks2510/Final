package trouble3;

public class Player {
	static int playerCount = 0;
	int playerID;
	//user entered nic name of the player
	String name = new String();
	//this designates the amount of pieces the player will have
	int pieces = 2;
	//this is the array the players pieces are held in
	Piece[] piece = new Piece[pieces];
	//this is the starting/finishing point on the board for this player
	int startPosition;
	//mian constructor
	Player(String name, int startPosition) {
		this.name = name;
		playerID = playerCount;
		this.startPosition = startPosition;
		for (int i = 0; i < piece.length; i++) {
			piece[i] = new Piece(startPosition, i, this);
		}
		playerCount++;
	}
	//this returns whether both of the players pieces have finished
	public boolean hasWon() {
		if (this.piece[0].isFinished() && this.piece[1].isFinished())
			return true;
		return false;
	}
	//this will perform the move or a players piece
	public void move(int destination, Piece piece){
		Board.blankSpace(piece.getIndex());
		piece.setIndex(destination);
		Board.Spaces[destination] = piece;
		if(Turn.winCheck()){
			//has won method
		}
		else{
			Turn.nextTurn();
		}
	}
	//this will check the conditions needed to move than move the piece if possible
	public boolean checkAndMove(int roll, Piece piece) {
		int destination = Checks.destinationCheck(roll + piece.getIndex());
		if(piece.isOnBoard()){
			if (Checks.destinationFree(destination)){
				move(destination,piece); ///change
				
				piece.setCounter(piece.getCounter() + roll);
				if(piece.getCounter() > 35)
					piece.finished();
				return false;
			}
			else if (Checks.whoIsThere(destination) == this) {
				System.out.print("its me");
				return true;
			} else if(Checks.whoIsThere(destination) != this){
				System.out.print("bump");
				Board.Spaces[destination].bumped();
				move(destination, piece);
				piece.setCounter(piece.getCounter() + roll);
				if(piece.getCounter() > 35)
					piece.finished();
				//add wincheck 
				return false;
			}
		}
		else{
			System.out.print("That piece is not on the board.");
		}
		return false;
	}
	//this will bring a piece onto the board
	public void comeOut(Piece piece) {
		piece.setOnBoard(true);
		Board.Spaces[piece.getIndex()] = piece;
		Turn.nextTurn();
	}
	
	// need to write a checkAndMove method
	public boolean checkAndComeOut(int roll, Piece piece) {
		int destination = Checks.destinationCheck(roll + piece.getIndex());
		if(!piece.isOnBoard()){
			if (Checks.destinationFree(destination)){
				comeOut(piece); ///change
			}
			else if (Checks.whoIsThere(destination) == this) {
				//invalid move message
				return true;
			} else if(Checks.whoIsThere(destination) != this){
				System.out.print("bump");
				Board.Spaces[destination].bumped();
				comeOut(piece);
				return false;
			}
		}
		else{
			System.out.print("That piece is not on the board.");
		}
		return false;
	}
	
	//this will check the conditions needed to move than move the piece if possible
//	public boolean selectAndBring() {
//		if (Checks.destinationFree(startPosition)){
//				System.out.print("empty space");
//			if (this.piece[0].isOnBoard() == false) {
//				comeOut();
//				return false;
//			} else {
//				comeOut(1);
//				return false;
//			}
//		}
//			else if (Checks.whoIsThere(startPosition) == this) {
//				System.out.print("Impossible move.");
//				return true;
//		}
//			else if (Checks.whoIsThere(startPosition) != this){
//				System.out.print("bump");
//				Board.Spaces[startPosition].bumped();
//				if (this.piece[0].isOnBoard() == false) {
//					comeOut(0);
//					return false;
//				} else {
//					comeOut(1);
//					return false;
//				}
//			}
//		return true;
//	}
	//this displays the information of the player and their pieces
	public void showPlayerInfo() {
		System.out.printf("\nName of player: %s --------------------------------------------\n"  
							+ "ID: %s\t\t\t\t|ID: %s\n"
							+ "\tIndex: %d\t\t\t|\tIndex: %d\n" 
							+ "\tcounter: %d\t\t\t|\tCounter: %d\n"  
							+ "\tOn Board: %b\t\t\t|\tOn Board: %b\n" 
							, name, piece[0].buttonID, piece[1].buttonID, piece[0].getIndex() , piece[1].getIndex(), piece[0].getCounter() , piece[1].getCounter(), piece[0].isOnBoard(), piece[1].isOnBoard() );
	}
}
