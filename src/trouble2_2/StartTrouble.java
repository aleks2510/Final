package trouble2_2;

import java.util.Scanner;

public class StartTrouble {

	public static void main(String[] args) {
		// set up
		rules();
		Scanner input = new Scanner(System.in);
		Player[] players = Action.createPlayers();
		Board.blankBoard();

		// this will decide the current player.
		int turnCounter = 0;
		// this holds the value of this turns roll
		int roll;
		
		// Start the game until 2 pieces from the same player get WON!
		while (!players[0].hasWon() && !players[1].hasWon()) {
			
			//Creating a board
			Board.printBoard(players);
			
			//Getting the current player
			Player player = players[turnCounter % 2];
			
			// roll
			System.out.printf("%s's turn, enter 'r' to roll: ", player.name);
			//user input to roll
			input.next();
			roll = Die.roll();
			//display the roll
			System.out.println("You rolled a: " +roll);
			//checks how many pieces the player has on the board.
			int ch = Checks.onBoard(player);
			//checks if the player can move
			if( ch == 2  && roll != 6){
				System.out.print("No Avialable Moves!!!\n");
				turnCounter++;
				continue;
			}else{
			//prints the options for movement the current player has for this turn.
			statements(ch ,roll);
			}
			
			int op = 0;
				//this will be used to ensure the move was made.
				boolean again = true;
				while(again){
				while(true){
				//Getting option from user and make sure it is valid					
					op = Action.getNumber(); 		
				if (op > -1 && op< 2){
					break;
				}else{
					System.out.print("Wrong input. Try it again\n");
					//System.out.print("Enter 0 to bring a new piece out, or Enter 1 to move a piece:");
					if( ch == 2 && roll != 6){
						System.out.print("No Avialable Moves!!!");
					}else{
					//prints the options for movement the current player has for this turn.
					statements(ch ,roll);
					}
				}
				}
				//input from the user picks which move to make
				switch (op) {
				//bring out
				case 0:
					if(roll == 6){
						again = player.selectAndBring(); break;
					}else {
							System.out.print("You may only come out on a roll of 6."); break;
					}
				//move
				case 1:
						//Getting option from user on which piece to use.
						while(true){
							System.out.print("Which piece to move 1 or 2?");
							op = Action.getNumber();
						if (op > 0 && op< 3){
							break;
						}else{
							System.out.print("Wrong input. Try it again\n");
							System.out.print("Which piece to move 1 or 2?");
						}
						}
						switch (op) {
						//move piece one
						case 1: 
							if(player.piece[0].onBoard){
								again = player.checkAndMove(roll, 0); break;
							}else{
									System.out.print("That piece is not on the board."); break;
								}
						//move piece two
						case 2: 
							if(player.piece[1].onBoard){
								again = player.checkAndMove(roll, 1); break;
							}else{
								System.out.print("That piece is not on the board."); break;
							}
						}
				}
			//indicator of turn (incrementation)
			if(again == false)
				turnCounter++;
			//if you rolled a six roll again
			if(roll == 6)
				turnCounter--;
		}
			//for clarity it clears the space above the current turn
			for(int i = 0; i < 10; i++){
			System.out.println("\n\n\n");
		}	
		}
		//Displays the winner once the winning condition within the game loop is met.
		System.out.printf("\n\n!!%s has won!!",players[turnCounter % 2]);
		Board.winnerBoard(players[turnCounter % 2]);
	}
	//print the rules
	public static void rules() {
		Scanner input = new Scanner(System.in);
		System.out
				.print("************ Star Trouble*********************\n"
						+ "Trouble is a game in which players roll a die and try to navigate\n"
						+ "their 2 pieces around the board without getting bumped back home.\n"
						+ " The first player to get all 2 pieces finished wins the game.\n\n"
						+ "Rules\n"
						+ "Who goes first:  players roll the die, the player that rolls the first six will make the first move.\n"
						+ "Movement: players must roll a 6 to move one of their 2 pieces on to the starting area.\n"
						+ "Conditions: \n"
						+ "		3.1 Players cannot have more than one piece on the same location at a time.\n"
						+ "		3.2 Landing on a space occupied by another player's piece will bump the other player�s piece home\n"
						+ "		3.3 if a 6 is rolled the player gets to roll again\n"
						+ "		4. Winner: The winner will be the player who gets both pieces into the finished area\n");
				

	}

	public static void statements(int ch, int roll){
		if( ch == 2 && roll == 6){
			System.out.print("Enter 0 to bring a new piece out");
		}else if( ch == 1 && roll == 6 ){
				System.out.print("Enter 0 to bring a new piece out, or Enter 1 to move a piece:");
			}else if( ch == 1 || roll!=6){
				System.out.print("Enter 1 to move a piece:");
			}else if( ch ==0){
				System.out.print("Enter 1 to move a piece:");
			}
			
	}
}