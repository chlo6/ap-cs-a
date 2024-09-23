/**
 *	The game of Pig.
 * 
 * 	Play game: User vs Computer. Roll the dice until you roll a 1 or 
 * 	choose to store your current round's rolled total, which will be 
 *  	added towards your total game score. If you roll a 1, your current 
 *  	turn's total becomes 0 and you lose your turn. Then, the computer
 *  	plays until it rolls a 1 or holds 20 or more in that turn. To win,
 *  	reach 100 total points before the computer does.
 * 
 *  	Statistics: simulates and records the probability of ending
 *  	each turn with a score of 0, 20, 21, 22, 23, 24, or 25. 
 * 
 *  	To run, cd into the directory with PigGame.java, Dice.java, and
 * 	Prompt.java, then type in your terminal "javac -source 1.8 -target 
 *  	1.8 PigGame.java" then "java PigGame" and it should run

 *	@author	Chloe He
 *	@since	9.13.2024
 */
 
import java.util.Scanner;

public class PigGame {
	
	/** 
	 * Declaring the variables 
	 */	 
	private	String	player = "user";	 // whose turn it is
	private int	userTurnScore;		 // user's current turn score
	private	int 	userTotalScore;  	 // user's current total score
	private int 	compTurnScore;		 // computer's current turn score
	private int 	compTotalScore;  	 // computer's current total score
	private final 	int WIN_SCORE;	 	 // score needed to win
	private final 	int TURN_END_SCORE;  	 // score needed for computer to hold turn
	private final 	int DISTR_SIZE;	 	 // max turn score for probability distribution array
	private final 	int LOSE_TURN_SCORE; 	 // dice roll that makes you lose your turn
	private Dice	dice;			 // dice object - used for rolling & printing dice

	/** 
	 * This constructor initializes the field variables and objects
	 */
	public PigGame() {
		userTurnScore = 0;
		userTotalScore = 0;
		compTurnScore = 0;
		compTotalScore = 0;
		WIN_SCORE = 100;
		TURN_END_SCORE = 20;
		DISTR_SIZE = 26;
		LOSE_TURN_SCORE = 1;
		dice = new Dice();
	}
	
	/**
	 * This is the main method, and runs the runner method which 
	 * runs each turn or finds the statistics
	 */
	public static void main (String [] args) {
		PigGame pg = new PigGame();
		pg.runner();
	}
	
	/** 
	 * If playing the game, this method starts every turn resets the
	 * turn score after each turn. It also checks if the game has ended.  
	 * If finding statistics, it runs the method that simulates and
	 * records the statistics. 
	 */
	public void runner() {
		printIntroduction();
		
		String option = Prompt.getString("Play game or Statistics (p or s) ");
		System.out.println();
		
		// if the user wants to play
		if (option.equals("p")) { 
			// while the game isn't over
			while (userTurnScore + userTotalScore < WIN_SCORE && 
				   compTurnScore + compTotalScore < WIN_SCORE) {
				// if user's turn
				if (player.equals("user")) {
					System.out.println("* * * * USER Turn * * * *\n");
					turn();
					userTurnScore = 0;
				}
				// if computer's turn
				else {
					System.out.println("* * * * COMPUTER's Turn * * * *\n");
					turn();
					compTurnScore = 0;
				}
			}
			
			// if someone won
			if (userTotalScore + userTurnScore > WIN_SCORE) {
				System.out.println("Congratulations!!! YOU WON!!!!\n" +
								   "Thanks for playing the Pig Game!!!\n");
			} else { 
				System.out.println("Too bad. COMPUTER WON.\n\nThanks "+
								   "for playing the Pig Game!!!\n");
			}
		} 
		
		// if user wants to see statistics
		else {
			findProbability();
		}
		
	}
	
	/**	
	 * This method prints the introduction to the game
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
	 * This method simulates each action (roll or hold) by prompting 
	 * for user inputs, then acts accordingly, e.g. ending the turn and
	 * resetting the turn total for a dice roll of 1, holding when user
	 * chooses to or computer reaches 20+ points, or calling the next 
	 * turn. 
	 * The dice is rolled by calling methods from the Dice.java file
	 */
	public void turn() {
		
		// if it's the user's turn
		if (player.equals("user")) {
			System.out.println("Your turn score:\t" + userTurnScore);
			System.out.println("Your total score:\t" + userTotalScore);
			String ch = Prompt.getString("\n(r)oll or (h)old ");
			
			// if the user decides to roll
			if (ch.equals("r")) { 
				System.out.println("\nYou ROLL");
				
				int numRolled = dice.roll();
				dice.printDice();
				
				userTurnScore += numRolled;
				
				// if the user rolls a 1 -> lose turn and turn score
				if (numRolled == LOSE_TURN_SCORE) { 
					System.out.println("You LOSE your turn.");
					System.out.println("Your total score: " + userTotalScore + "\n");
					player = "computer";
				} 
				// otherwise: user's turn again
				else turn(); 
				
			} 
			
			// if the user decides to hold
			else if (ch.equals("h")) { 
				userTotalScore += userTurnScore;
				System.out.println("\nYou HOLD");
				System.out.println("Your total score: " + userTotalScore + "\n");
				player = "computer";
			}
			
			// if the user doesn't type r or h
			else {
				System.out.println("Please type r or h");
				turn();		
			}
		} 
		
		// if it's the computer's turn
		else {
			System.out.println("Computer's turn score:\t" + compTurnScore);
			System.out.println("Computer's total score:\t" + compTotalScore);
			Prompt.getString("\nPress enter for computer's turn ");
			
			System.out.println("\nComputer will ROLL ");
			int numRolled = dice.roll();
			dice.printDice();
			compTurnScore += numRolled;
			
			// if the computer rolls a 1 -> lose turn and score
			if (numRolled == LOSE_TURN_SCORE) { 
				System.out.println("Computer loses turn.");
				System.out.println("Computer's total score: " + compTotalScore + "\n");
				player = "user";
			} 
			// if the computer holds (reached turn score of 20 or more)
			else if (compTurnScore >= TURN_END_SCORE) {
				System.out.println("Computer will HOLD");
				compTotalScore += compTurnScore;
				System.out.println("Computer's total score: " + compTotalScore + "\n");
				player = "user";
			}
			// next turn
			else turn(); 
		}
		
	}	
	
	/**
	 * This method is used for finding the statistics. It simulates
	 * the computer's turns, which is automatically holding at 20 or 
	 * more or rolling a 1 (resulting in a score of 0), n times 
	 * (depending on the user's input), and prints a resultant 
	 * probability table in the terminal. 
	 */
	public void findProbability() {
		final int MIN_TURNS = 1000, MAX_TURNS = 10000000;
		System.out.println("Run statistical analysis - \"Hold at 20\"\n");
		int turns = Prompt.getInt("Number of turns", MIN_TURNS, MAX_TURNS);
		System.out.print("\n\tEstimated\nScore\tProbability\n");

		// array which holds # of occurences of each score
		float [] distr = new float [DISTR_SIZE]; 
			
		// simulation
		for (int i = 0; i < turns; i++) {
			int total = 0;
			int numRolled = 0;
			while (numRolled != LOSE_TURN_SCORE && total < TURN_END_SCORE) {
				numRolled = dice.roll();
				total += numRolled; 				
			}
			if (numRolled == 1) total = 0;
			distr[total] ++;
		}
		
		// printing out the results
		for (int i = 0; i < DISTR_SIZE; i++) distr[i] = distr[i]/turns;
		System.out.printf(" 0\t%.5f\n",distr[0]);
		for (int i = 20; i < DISTR_SIZE; i++) {
			System.out.printf("%d\t%.5f\n", i, distr[i]);
		}
		System.out.println();
	}
}
