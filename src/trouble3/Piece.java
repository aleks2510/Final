package trouble3;
import javax.swing.ImageIcon;
import java.awt.*;
public class Piece {
	//default ID/display of the piece
	ImageIcon boardID = new ImageIcon("Assets/Floor/floor.png");
	ImageIcon buttonID;
	ImageIcon mouseOverID;
	ImageIcon clickedID;
	//refer. to the player who owns the piece
	Player owner;
	//the start finish point of the piece
	private int startPossition;
	//this is where the piece is on the board
	private int index = 0;
	//this is how many places this piece has moved
	private int counter = 0;
	//shows whether the piece is on the board
	private boolean onBoard = false;
	//shows if the piece has finished the game
	private boolean isFinished = false;
	//main constructor(used for player pieces)
	public Piece(int startPossition, int pieceID, Player owner) {
		if(owner.playerID == 0){
			if(pieceID == 0){
				this.boardID = new ImageIcon("Assets/Floor/lfloor.png");
				this.buttonID = new ImageIcon("Assets/Icons/luke.png");
				this.mouseOverID = new ImageIcon("Assets/Icons/lukeMo.png");
				this.clickedID = new ImageIcon("Assets/Icons/lukeCk.png");
			}
			else{
				this.boardID = new ImageIcon("Assets/Floor/rfloor.png");
				this.buttonID = new ImageIcon("Assets/Icons/r2d2.png");
				this.mouseOverID = new ImageIcon("Assets/Icons/r2d2Mo.png");
				this.clickedID = new ImageIcon("Assets/Icons/r2d2Ck.png");
			}
		}
		else{
			if(pieceID == 0){
				this.boardID = new ImageIcon("Assets/Floor/dfloor.png");
				this.buttonID = new ImageIcon("Assets/Icons/darth.png");
				this.mouseOverID = new ImageIcon("Assets/Icons/darthMo.png");
				this.clickedID = new ImageIcon("Assets/Icons/darthCk.png");	
			}
			else{
				this.boardID = new ImageIcon("Assets/Floor/tfloor.png");
				this.buttonID = new ImageIcon("Assets/Icons/trooper.png");
				this.mouseOverID = new ImageIcon("Assets/Icons/trooperMo.png");
				this.clickedID = new ImageIcon("Assets/Icons/trooperCk.png");	
			}
		}
		
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
			boardID = new ImageIcon("Assets/Archive/FloorP!.png");
			isFinished = true;
			index = startPossition;
			counter = 0;
			onBoard = false ; 
			return true;
		}
		return false;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public int getStartPossition() {
		return startPossition;
	}
	public void setStartPossition(int startPossition) {
		this.startPossition = startPossition;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public boolean isOnBoard() {
		return onBoard;
	}
	public void setOnBoard(boolean onBoard) {
		this.onBoard = onBoard;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
}
