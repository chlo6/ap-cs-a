/**
 *	The game of Pig.
 *	Roll the dice until you choose to store your current rolled total or until you get a 1, which sets your current rolled total to 0.
 *  Playing against computer which always holds when it gets 20 or more
 * 
 *	@author	Chloe He
 *	@since	9.13.2024
 */
 
 // java -cp PigGame.jar PigGame
 // find losing message, roll 1 message
import java.util.Scanner;
public class PigGame {
	/** Runs the pig game */
	
	/** 
	 * Declaring the variables 
	 */
	private String	player = "user";
	private int		userTurnScore, userTotalScore; 
	private int 	compTurnScore, compTotalScore;
	private Scanner scanner;
	private Dice	dice;
	
	/** 
	 * Constructor for initializing variables 
	 */
	public PigGame() {
		userTurnScore = 0;
		userTotalScore = 0;
		compTurnScore = 0;
		compTotalScore = 0;
		scanner = new Scanner(System.in);
		dice = new Dice();
	}
	
	/**
	 * Main class, runs the runner method 
	 */
	public static void main (String [] args) {
		PigGame pg = new PigGame();
		pg.runner();
	}
	
	/** 
	 * Starts every user/computer switch, checks if game ended 
	 */
	public void runner() {
		printIntroduction();
		
		System.out.print("Play game or Statistics (p or s) -> ");
		String option = scanner.next();
		System.out.println();
		
		if (option.equals("p")) {
			while (userTurnScore + userTotalScore < 100 && compTurnScore + compTotalScore < 100) {
				if (player.equals("user")) System.out.println("* * * * USER Turn * * * *\n");
				else System.out.println("* * * * COMPUTER's Turn * * * *\n");
				turn();
			}
			
			if (userTotalScore > 100) {
				System.out.println("Congratulations!!! YOU WON!!!!\nThanks for playing the Pig Game!!!");
			} else { // FIX???
				System.out.println("Too bad. COMPUTER WON.\n\nThanks for playing the Pig Game!!!");
			}
		} else {
			System.out.println("Run statistical analysis - \"Hold at 20\"\n");
			System.out.print("Number of turns (1000 - 10000000) -> ");
			int turns = scanner.nextInt();
			scanner.next();
			System.out.println("\nScore\tEstimated Probability");
			
			
		}
		
	}
	
	/**	
	 * Print the introduction to the game
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
	/** 
	 * Method for simulating each turn
	 */
	public void turn() {
		
		/** if user is playing... */
		if (player.equals("user")) {
			System.out.println("Your turn score:\t" + userTurnScore);
			System.out.println("Your total score:\t" + userTotalScore);
			System.out.print("\n(r)oll or (h)old -> ");
			String ch = scanner.next();
			
			if (ch.equals("r")) { /** if user rolls */
				System.out.println("\nYou ROLL");
				
				int numRolled = dice.roll();
				dice.printDice();
				
				userTurnScore += numRolled;
				
				if (numRolled == 1) { /** if user rolls a 1 */
					System.out.println("You LOSE your turn.");
					System.out.println("Your total score: " + userTotalScore + "\n");
					player = "computer";
					userTurnScore = 0;
				} 
				else turn(); /** user's turn again */
			} else { /** if user holds */
				System.out.println("\nYour total score:\t" + userTotalScore);
				userTotalScore += userTurnScore;
				player = "computer";
			}			
		} 
		
		/** if computer is playing... */
		else {
			System.out.println("Computer's turn score:\t" + compTurnScore);
			System.out.println("Computer's total score:\t" + compTotalScore);
			System.out.print("\nPress enter for computer's turn ->");
			scanner.nextLine();
			
			System.out.println("\nComputer will ROLL ");
			int numRolled = dice.roll();
			dice.printDice();
			compTurnScore += numRolled;
			
			if (numRolled == 1) { /** if computer rolls a 1 */
				System.out.println("Computer loses turn.");
				System.out.println("Computer total score: " + compTotalScore + "\n");
				player = "user";
				compTurnScore = 0;
			} /** if computer holds */
			else if (compTurnScore >= 20) System.out.println("Computer will HOLD");
			else turn(); /** computer's turn again */
		}
		
	}	
}
