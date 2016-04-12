package trouble2_2;

public class Player {
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
		this.startPosition = startPosition;
		for (int i = 0; i < piece.length; i++) {
			piece[i] = new Piece(startPosition, '|' + name + (i + 1) + '|',
					this);
		}
	}
	//this returns whether both of the players pieces have finished
	public boolean hasWon() {
		if (this.piece[0].isFinished && this.piece[1].isFinished)
			return true;
		return false;
	}
	//this will perform the move or a players piece
	public void move(int destination, int piece){
		Board.blankSpace(this.piece[piece].index);
		this.piece[piece].index = destination;
		Board.Spaces[destination] = this.piece[piece];
	}
	//this will check the conditions needed to move than move the piece if possible
	public boolean checkAndMove(int roll, int piece) {
		int destination = Checks.destinationCheck(roll + this.piece[piece].index);
		if(this.piece[piece].onBoard){
			if (Checks.destinationFree(destination)){
				move(destination,piece); ///change
				
				this.piece[piece].counter += roll;
				if(this.piece[piece].counter > 35)
					this.piece[piece].finished();
				return false;
			}
			else if (Checks.whoIsThere(destination) == this) {
				System.out.print("its me");
				return true;
			} else if(Checks.whoIsThere(destination) != this){
				System.out.print("bump");
				Board.Spaces[destination].bumped();
				move(destination, piece);
				this.piece[piece].counter += roll;
				if(this.piece[piece].counter > 35)
					this.piece[piece].finished();
				return false;
			}
		}
		else{
			System.out.print("That piece is not on the board.");
		}
		return false;
	}
	//this will bring a piece onto the board
	public void comeOut(int piece) {
		this.piece[piece].onBoard = true;
		Board.Spaces[this.piece[piece].index] = this.piece[piece];
	}
	//this will check the conditions needed to move than move the piece if possible
	public boolean selectAndBring() {
		if (Checks.destinationFree(startPosition)){
				System.out.print("empty space");
			if (this.piece[0].onBoard == false) {
				comeOut(0);
				return false;
			} else {
				comeOut(1);
				return false;
			}
		}
			else if (Checks.whoIsThere(startPosition) == this) {
				System.out.print("Impossible move.");
				return true;
		}
			else if (Checks.whoIsThere(startPosition) != this){
				System.out.print("bump");
				Board.Spaces[startPosition].bumped();
				if (this.piece[0].onBoard == false) {
					comeOut(0);
					return false;
				} else {
					comeOut(1);
					return false;
				}
			}
		return true;
	}
	//this displays the information of the player and their pieces
	public void showPlayerInfo() {
		System.out.printf("\nName of player: %s --------------------------------------------\n"  
							+ "ID: %s\t\t\t\t|ID: %s\n"
							+ "\tIndex: %d\t\t\t|\tIndex: %d\n" 
							+ "\tcounter: %d\t\t\t|\tCounter: %d\n"  
							+ "\tOn Board: %b\t\t\t|\tOn Board: %b\n" 
							, name, piece[0].ID, piece[1].ID, piece[0].index , piece[1].index, piece[0].counter , piece[1].counter, piece[0].onBoard, piece[1].onBoard );
	}
}
