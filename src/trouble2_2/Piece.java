package trouble2_2;

public class Piece {
	//default ID/display of the piece
	String ID = "|____|";
	//ref. to the player who owns the piece
	Player owner;
	//the start finish point of the piece
	int startPossition;
	//this is where the piece is on the board
	int index = 0;
	//this is how many places this piece has moved
	int counter = 0;
	//shows whether the piece is on the board
	boolean onBoard = false;
	//shows if the piece has finished the game
	boolean isFinished = false;
	//main constructor(used for player pieces)
	Piece(int startPossition, String ID, Player owner) {
		this.ID = ID;
		this.owner = owner;
		this.startPossition = startPossition;
		this.index = startPossition;
	}
	//no arg. (used for board creation)
	Piece() {
	}
	//the action of getting your piece bumped
	public void bumped(){
		System.out.print("Got Bumped!!!!!!!!!\n");
		index = startPossition;
		counter = 0;
		onBoard = false ; 
	}
	//the action of finishing the piece
	public boolean finished() {
		if (counter > 35) {
			Board.blankSpace(index);
			ID = "|WON!|";
			isFinished = true;
			index = startPossition;
			counter = 0;
			onBoard = false ; 
			return true;
		}
		return false;
	}
}
