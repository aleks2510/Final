package trouble2_2;

public class Rules {
	//self explained
	static String rules = new String(""
			+ " Welcome to Star Trouble Game\n"
			+ " 1. Who goes first: Players roll the die, the player that rolls the first six will make the first move.\n"
			+ " 2. Movement: players must roll a 6 to move one of their 2 pieces on to the starting area.\n"
			+ " 3. Conditions:\n"
			+ "\t Players cannot have more than one piece on the same location at a time.\n"
			+ "\t Landing on a space occupied by another player's piece will bump the other player�s piece home.\n"
			+ "\t If a 6 is rolled the player gets to roll again.\n"
			+ " 4. Winner: The winner will be the player who gets both pieces into the finished area.\n"
 );
	public Rules(){
		
	}

	public static void printRules(){
		System.out.println(Rules.rules);
		
	}
	
	
	
	
}
